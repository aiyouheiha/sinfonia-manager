# Sinfonia Manager

- [Feign](#feign)
- [hystrix](#hystrix)

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




