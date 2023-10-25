# codegen-kotlin-demo

codegen-kotlin-demo 프로젝트에 오신것을 환영합니다!

## 소개

본 레포지토리는 [OpenAPI CodeGenerator](https://github.com/OpenAPITools/openapi-generator) 프로젝트의 Gralde 플러그인을 활용하는 예제입니다.

Gradle Task를 활용하여 OpenAPI Spec으로 명세를 기반으로 API Interface, DTO를 생성합니다.

> 본 프로젝트는 Kotlin Spring 프로젝트를 기반으로 하고있습니다.
>
> gradle 문법이 Kotlin DSL 형태로 작성되어있습니다.
>
> groovy 문법을 확인하고 싶다면 [codegen-spring-demo 프로젝트](https://github.com/Choi-JJunho/codegen-spring-demo)를 참고해주세요

## 실행방법

```bash
$ git clone https://github.com/Choi-JJunho/codegen-kotlin-demo.git

$ cd codegen-kotlin-demo

$ ./gradlew build
```

## 사용 방법

<img width="500" alt="image" src="https://github.com/Choi-JJunho/codegen-spring-demo/assets/49794401/6f63067a-7db8-4afd-b64b-1026bee840bd">

[OpenAPI Specification 명세](https://spec.openapis.org/oas/v3.1.0)에 맞춰 yaml 문서를 작성하여 프로젝트의 contract 경로에 추가합니다.

<img width="500" alt="image" src="https://github.com/Choi-JJunho/codegen-spring-demo/assets/49794401/929217bc-5f89-436d-ba39-80c4e4a8b0c9">

`gradle build` 를 수행하면 `build/generated` 경로에 명세를 기반으로 한 코드들이 생성됩니다.

## 커스텀 하기

gradle plugin을 보다 상세하게 활용하고 싶다면 다음 문서를 참고하여 gradle script를 수정할 수 있습니다.

- [OpenAPI Generator Gradle Plugin](https://github.com/OpenAPITools/openapi-generator/blob/master/modules/openapi-generator-gradle-plugin/README.adoc)
- [kotlin spring generator docs](https://openapi-generator.tech/docs/generators/kotlin-spring)
