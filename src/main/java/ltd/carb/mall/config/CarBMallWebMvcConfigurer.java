 
package ltd.carb.mall.config;

import ltd.carb.mall.common.Constants;
import ltd.carb.mall.interceptor.AdminLoginInterceptor;
import ltd.carb.mall.interceptor.CarBMallCartNumberInterceptor;
import ltd.carb.mall.interceptor.CarBMallLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CarBMallWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private AdminLoginInterceptor adminLoginInterceptor;
    @Autowired
    private CarBMallLoginInterceptor CarBMallLoginInterceptor;
    @Autowired
    private CarBMallCartNumberInterceptor CarBMallCartNumberInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {



//        registry.addInterceptor(adminLoginInterceptor)
//                .addPathPatterns("/admin/**")
//                .excludePathPatterns("/admin/login")
//                .excludePathPatterns("/admin/dist/**")
//                .excludePathPatterns("/admin/plugins/**");
//


        registry.addInterceptor(CarBMallCartNumberInterceptor)
                .excludePathPatterns("/admin/**")
                .excludePathPatterns("/register")
                .excludePathPatterns("/login")
                .excludePathPatterns("/logout");

        registry.addInterceptor(CarBMallLoginInterceptor)
                .excludePathPatterns("/admin/**")
                .excludePathPatterns("/register")
                .excludePathPatterns("/login")
                .excludePathPatterns("/logout")
                .addPathPatterns("/cars/detail/**")
                .addPathPatterns("/shop-cart")
                .addPathPatterns("/shop-cart/**")
                .addPathPatterns("/saveOrder")
                .addPathPatterns("/orders")
                .addPathPatterns("/orders/**")            
                .addPathPatterns("/personal")
                .addPathPatterns("/personal/updateInfo")
                .addPathPatterns("/selectPayType")
                .addPathPatterns("/payPage");
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + Constants.FILE_UPLOAD_DIC);
        registry.addResourceHandler("/cars-img/**").addResourceLocations("file:" + Constants.FILE_UPLOAD_DIC);
    }
}
