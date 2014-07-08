package repository

import domain.UpVote
import org.scalatest.{FeatureSpec, GivenWhenThen}
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2014/06/06.
 */
class UpVoteRepositoryTest extends FeatureSpec with GivenWhenThen {

  feature("Save The Vote into Repository")
  {
    info("As a User")
    info("I want to be able to Save a Vote Into The Respository")
    info("So that I can Use it to Count Total Votes")

    scenario("Saved Vote in the Database") {

      Given("Given a Role Object")
      val vote1 = UpVote("Subject1","User1","YES")
      val vote2 = UpVote("Subject1","User2","YES")
      val vote3 = UpVote("Subject2","User3","YES")
      val vote4 = UpVote("Subject2","User1","YES")
      val vote5 = UpVote("Subject3","User1","YES")
      val vote6 = UpVote("Subject3","User3","YES")
      When("When I save it Using a Respository")
        val repository = UpVoteRepository
        repository.save(vote1)
        repository.save(vote2)
        repository.save(vote3)
        repository.save(vote4)
        repository.save(vote5)
        repository.save(vote6)
      Then("Then I should be able to retrieve it using the ID")


        val subjects = Await.result(repository.getVotesBySubjectId("Subject1"), 5000 millis)
        val voters = Await.result(repository.deleteVoteBySubjectAndVoterId("Subject1", "User1"), 5000 millis)
        val voterAndRole = Await.result(repository.getVoteBySubjectAndVoterId("Subject1","User1"), 5000 millis)


      Then(" Votes By Subject Are "+subjects.size)
//      Then(" Votes By User  Are "+voters.size)
      Then(" User Voter is  "+voterAndRole)

      Given("Given the ID ")

//        val res = Await.result(repository.getAllRoles, 5000 millis)
//        assert(res.size > 0)
//      And("AND THE ROLE IS ADMIN with SIZE "+res.size)


    }
  }
}