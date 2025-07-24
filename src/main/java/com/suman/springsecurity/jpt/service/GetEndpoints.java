package com.suman.springsecurity.jpt.service;

import com.suman.springsecurity.validator.ExcludeEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PathPatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class GetEndpoints {
    private final RequestMappingHandlerMapping requestMappingHandlerMapping;

    public GetEndpoints(@Qualifier("requestMappingHandlerMapping") RequestMappingHandlerMapping requestMappingHandlerMapping) {
        this.requestMappingHandlerMapping = requestMappingHandlerMapping;
    }

    public Map<String, Set<String>> getAllApiEndpointsByController() {
        Map<RequestMappingInfo, HandlerMethod> requestMappings = requestMappingHandlerMapping.getHandlerMethods();

        Map<String, Set<String>> endpointsByController = requestMappings.entrySet()
                .stream()
                .filter(this::isRestControllerMethodWithoutExcludeEndpoint)
                .collect(Collectors.groupingBy(
                        entry -> entry.getValue().getBeanType().getSimpleName(),
                        Collectors.flatMapping(
                                this::getEndpointPatterns,
                                Collectors.toSet()
                        )
                ));

        log.info("Fetched API endpoints by controller: {}", endpointsByController);
        return endpointsByController;
    }

    private boolean isRestControllerMethodWithoutExcludeEndpoint(Map.Entry<RequestMappingInfo, HandlerMethod> entry) {
        HandlerMethod handlerMethod = entry.getValue();

        boolean isRestController = handlerMethod.getBeanType().isAnnotationPresent(RequestMapping.class);

        boolean isControllerExcluded =handlerMethod.getBeanType().isAnnotationPresent(ExcludeEndpoint.class) ||
                handlerMethod.getMethod().isAnnotationPresent(ExcludeEndpoint.class);


        return isRestController  && !isControllerExcluded;
    }

    private Stream<String> getEndpointPatterns(Map.Entry<RequestMappingInfo, HandlerMethod> entry) {
        RequestMappingInfo requestMappingInfo = entry.getKey();

        // Check for patterns condition first
        PatternsRequestCondition patternsCondition = requestMappingInfo.getPatternsCondition();
        if (patternsCondition != null && !patternsCondition.getPatterns().isEmpty()) {
            return patternsCondition.getPatterns().stream();
        }

        // Check for path patterns condition
        PathPatternsRequestCondition pathPatternsCondition = requestMappingInfo.getPathPatternsCondition();
        if (pathPatternsCondition != null && !pathPatternsCondition.getPatternValues().isEmpty()) {
            return pathPatternsCondition.getPatternValues().stream();
        }
        return Stream.empty();
    }
}
