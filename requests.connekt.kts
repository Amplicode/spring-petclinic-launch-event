import org.assertj.core.api.Assertions.assertThat

val host = "http://localhost:8080"


GET("http://localhost:8080/rest/petTypes") {
    contentType("application/json")
}

GET("http://localhost:8080/rest/vets") {
    contentType("application/json")
}

data class Vet(val firstName: String)

GET("http://localhost:8080/rest/vets/{id}") {
    contentType("application/json")
    pathParam("id", "3")
} then {
    val vet = jsonPath().read("$", Vet::class.java)
    assertThat(vet.firstName).isEqualTo("Linda")
}

GET("http://localhost:8080/rest/vets/byFirstName/{firstName}") {
    pathParam("firstName", "Linda")
}
