# Spring Cloud Zuul

- [Back to Home Page](https://aiyouheiha.github.io/)

------------------------------------------------------

- [参考](#参考)
- [0. 说明](#说明)
- [1. 添加依赖](#添加依赖)
- [2. 添加注解](#添加注解)
- [3. 添加配置](#添加配置)

------------------------------------------------------

## 参考

- [Router and Filter: Zuul](http://cloud.spring.io/spring-cloud-static/Dalston.SR2/#_router_and_filter_zuul)

------------------------------------------------------

### 说明

通过在当前微服务中配置Zuul，便可以通过当前服务，访问在服务注册中心注册的其他微服务。

e.g.
- `x.x.x.x:8888/v1/user` is belong to service "uc" (port 8888)
- Set zuul on service "manager" (port 9999)
- After legal setting, via `x.x.x.x:9999/uc/v1/user` could get response from "uc"

### 添加依赖

```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zuul</artifactId>
</dependency>
```

### 添加注解

> @EnableDiscoveryClient is necessary.

```
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
```

### 添加配置

> Zuul支持自动添加服务，如果在服务注册中心注册的名称便是自己需要的前缀，则不需要做额外的配置

#### Examples

> The name registered in discovery server is 'uc'.

```
# Without any setting, /uc/** is available.
```

```
# /user/center/** could be called success, but /uc/** now is unavailable.
zuul:
  routes:
    uc: /user/center/**
  ignored-services: '*'
```
```
# /user/center/** could be called success, but /uc/** now is unavailable.
zuul:
  routes:
    uc:
      path: /user/center/**
  ignored-services: '*'
```
```
# /user/center/** could be called success, but /uc/** now is unavailable.
zuul:
  routes:
    user-center:
      path: /user/center/**
      serviceId: uc
  ignored-services: '*'
```

```
# /uc and /user/center and /user-center is all unavailable.
zuul:
  routes:
    uc:
      path: /user/center/**
      serviceId: user-center
  ignored-services: '*'