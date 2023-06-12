package com.cybersolution.fazeal.logistics.interceptor;


//@Component
//public class FeignInterceptor implements RequestInterceptor {
//
//    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
//
//    @SneakyThrows
//    @Override
//    public void apply(RequestTemplate template) {
//    	System.out.println(template.headers().get(AppConstants.AUTHERIZATION));
//    	System.out.println(SecurityContextHolder.getContext().getAuthentication());
//        if (ObjectUtils.isEmpty(template.headers().get(AppConstants.AUTHERIZATION))
//                && !ObjectUtils.isEmpty(SecurityContextHolder.getContext().getAuthentication())) {
//        	System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//            CustomWebAuthenticationDetailsSource webAuthenticationDetailsSource = (CustomWebAuthenticationDetailsSource) SecurityContextHolder.getContext().getAuthentication().getDetails();
//            template.header(AppConstants.AUTHERIZATION, webAuthenticationDetailsSource.getToken());
//        }
//    }
//}