package controllers

import akka.util.Timeout
import com.typesafe.scalalogging.LazyLogging
import javax.inject._
import play.api.libs.json.Json
import play.api.mvc._
import views.html._

import scala.concurrent.ExecutionContext
import scala.concurrent.duration.DurationInt


@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents,
                               indexTemplate: index,
                               userT: users)
                              (implicit val ec: ExecutionContext)
  extends BaseController with LazyLogging {

  implicit val defaultTimeout = Timeout(60.seconds)

  def index = Action {
    Ok(indexTemplate())
  }
  def users = Action {
    Ok(userT())
  }

  def register() = Action(parse.json){ implicit request => {
    val email = (request.body \ "email").as[String]
    val password = (request.body \ "psw").as[String]
    val comment = (request.body \ "comment").asOpt[String]
    val sLanguages = (request.body \ "sLanguages").toOption
    val pLanguage = (request.body \ "pLanguage").asOpt[String]
    logger.info(s"requestBody: ${request.body}")
    logger.info(s"email: $email")
    logger.info(s"psw: $password")
    logger.info(s"comment: $comment")
    logger.info(s"sLanguages: $sLanguages")
    logger.info(s"pLanguage: $pLanguage")
      Ok(Json.toJson("Successful!"))
    }
  }

}
