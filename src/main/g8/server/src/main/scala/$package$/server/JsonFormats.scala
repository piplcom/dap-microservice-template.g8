package $package$.server

//#json-formats
import $package$.service.$name;format="lower,snake"$.ServiceApi._
import spray.json.DefaultJsonProtocol

object JsonFormats extends DefaultJsonProtocol {
  implicit val searchInputFormat = jsonFormat1($name;format="Camel"$Input)
  implicit val searchOutputFormat = jsonFormat1($name;format="Camel"$Output)
}