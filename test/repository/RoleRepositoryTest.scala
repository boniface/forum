package repository

import java.util.UUID

import domain.Role
import org.scalatest.{FeatureSpec, GivenWhenThen}

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

/**
 * Created by hashcode on 2014/06/06.
 */
class RoleRepositoryTest extends FeatureSpec with GivenWhenThen {

  feature("Save Role into Repository") {
    info("As a User")
    info("I want to be able to Save a Role Into The Respository")
    info("So that I can Use it to Assign to Other Authors")

    scenario("Saved Role in the Database") {

      Given("Given a Role Object")
      val role = Role(UUID.randomUUID(), "Administraor ROLE", "ADMIN")
      When("When I save it Using a Respository")
      val repository = RoleRepository
      repository.save(role)
      Then("Then I should be able to retrieve it using the ID")
      val savedRole = repository.getRoleById(role.id)
      savedRole map (role =>
        assert("ADMIN" == role.get.rolename)
        )
      Given("Given the ID " + role.description)


      val res = Await.result(repository.getAllRoles, 5000 millis)
      assert(res.size > 0)
      And("AND THE ROLE IS ADMIN with SIZE " + res.size)


    }
  }
}