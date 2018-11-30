package mikemcgowan

case class Logout(username: String) {

  def toJsonString: String =
    "{\"username\": \"%s\"}" format username

}
