package $package$.server

import akka.http.scaladsl.common.EntityStreamingSupport
import akka.http.scaladsl.marshallers.sprayjson.JsonEntityStreamingSupport
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Route
import com.github.swagger.akka.model
import com.github.swagger.akka.model.Info
import $package$.server.routes.Node
import $package$.service.$name;format="lower,snake"$.$name;format="Camel"$Service
import $package$.service.$name;format="lower,snake"$.ServiceApi._
import com.pipl.dap.webservice.core.Webservice
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.{Content, Schema}
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import javax.ws.rs.{POST, Path}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

trait Service extends Webservice {
  import JsonFormats._
  implicit val jsonStreamingSupport = EntityStreamingSupport.json()

  implicit def ec: ExecutionContext

  override protected def isReady: Future[Boolean] = Future.successful(true)

  lazy val $name;format="camel"$Service = $name;format="Camel"$Service(system.settings.config)(ec)

  @Tag(name = "$name;format="space"$", description = "description")
  @Path("/v1/echo")
  @POST
  @Operation(
    summary = "echo",
    description = "description",
    requestBody = new RequestBody(required = true,
      content = Array(new Content(
        mediaType = "application/json",
        schema = new Schema(implementation = classOf[$name;format="Camel"$Input])))),
    responses = Array(
      new ApiResponse(
        responseCode = "200",
        content = Array(
          new Content(
            mediaType = "application/json",
            schema = new Schema(implementation = classOf[$name;format="Camel"$Input])))))
  )
  def echoRoute: Route =
    post {
      entity(as[$name;format="Camel"$Input]) { input =>
        path("v1" / "echo") {
          onComplete( $name;format="camel"$Service.echo(input) ) {
            case Success(result) =>
              complete(OK -> result)

            case Failure(err) =>
              logger.error("Failed to serve search due to", err)
              complete(InternalServerError -> Option(err.getMessage).getOrElse(""))
          }
        }
      }
    }

  override lazy val serviceRoutes: Route = echoRoute ~ Node.route

  // swagger
  override def apiClasses: Set[Class[_]] = Set(this.getClass)

  // swagger
  override def info: Info = Info(
    description = "Use this tool to extract social network information via Google Search API",
    version = "Initial",
    title = "Social Network Extraction Service",
    contact = Some(model.Contact(name = "Data Collection", url = "", email = "" )))
}
