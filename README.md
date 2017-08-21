# Sinfonia Manager

- [feign](#feign)
- [hystrix](#hystrix)
- [zuul](#zuul)

## feign

> Based on Cloud Discovery `@EnableDiscoveryClient`, which could be eureka, consul or the like.

### 1. Pom

```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-feign</artifactId>
</dependency>
```

### 2. Add `@EnableFeignClients` on Main Class

```
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
```

### 3. Create Interface of Service Wanna Be Called

```
@FeignClient("uc")
public interface UserService {
    @RequestMapping(method = RequestMethod.POST)
    User createUser(@RequestBody User user);

    @RequestMapping(method = RequestMethod.GET)
    UserResult listUser(UserQuery userQuery);

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    User getUser(@PathVariable Integer userId);
}
```

> And then, this Service could be autowired and used under Controller or somewhere needed. 

### 4. Sum up about Feign

It seems hard to implement **requests pass-through** via feign. 
While lots of useless codes must be set via feign, ribbon work with restTemplate seems like a good way.

By the way, feign could load balance automatic without a ` @LoadBalanced` restTemplate bean which ribbon needed.

---------------------------------------------------------------------

## hystrix

### 1. Pom

```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-hystrix</artifactId>
</dependency>
```

### 2. `@EnableCircuitBreaker` or `@EnableHystrix` on Main Class

```
@EnableHystrix
@EnableDiscoveryClient
@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
```

> `@SpringCloudApplication` which contains `@SpringBootApplication`, `@EnableDiscoveryClient` 
> and `@EnableCircuitBreaker` could be considered to use.


## zuul

### 1. pom

```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zuul</artifactId>
</dependency>
```

### 2. Add @EnableZuulProxy to Main Class

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

### 3. Settings

- [Router and Filter: Zuul](http://cloud.spring.io/spring-cloud-static/Dalston.SR2/#_router_and_filter_zuul)

Support the service automatically add, if name registered in discovery server is the prefix wanna use, 
then extra setting is unnecessary.

If wanna make a customized setting, please open link above.

#### Example

> The name was registered in discovery server is 'uc'.

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
```





