import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class BenchmarkSimulation extends Simulation {
  val host = System.getProperty("host", "localhost")
  val port = Integer.getInteger("port", 8080)
  val url = "http://"+host+":"+port
  val httpProtocol = http
    .baseUrl(url) // Here is the root for all relative URLs
    .acceptHeader("text/html,application/json,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  val helloScn = scenario("hello") // A scenario is a chain of requests and pauses
    .exec(http("hello")
    .get("/hello")
//    .check(status.is(200), bodyString.is("""{"hello":"world"}"""))
  )

  val computeScn = scenario("compute")
    .exec(
    http("compute")
      .get("/compute")
//            .check(status.is(200), bodyString.is("""{"status":"done"}"""))
  )

  val countriesScn = scenario("countries") // A scenario is a chain of requests and pauses
    .exec(http("countries")
    .get("/countries")
//    .check(status.is(200), bodyString.is("""[{"id":1,"name":"Afghanistan"},{"id":2,"name":"Albania"},{"id":3,"name":"Algeria"},{"id":4,"name":"Andorra"},{"id":5,"name":"Angola"},{"id":6,"name":"Antigua and Barbuda"},{"id":7,"name":"Argentina"},{"id":8,"name":"Armenia"},{"id":9,"name":"Australia"},{"id":10,"name":"Austria"},{"id":11,"name":"Azerbaijan"},{"id":12,"name":"The Bahamas"},{"id":13,"name":"Bahrain"},{"id":14,"name":"Bangladesh"},{"id":15,"name":"Barbados"},{"id":16,"name":"Belarus"},{"id":17,"name":"Belgium"},{"id":18,"name":"Belize"},{"id":19,"name":"Benin"},{"id":20,"name":"Bhutan"},{"id":21,"name":"Bolivia"},{"id":22,"name":"Bosnia"},{"id":23,"name":"Herzegovina"},{"id":24,"name":"Botswana"},{"id":25,"name":"Brazil"},{"id":26,"name":"Brunei"},{"id":27,"name":"Bulgaria"},{"id":28,"name":"Burkina Faso"},{"id":29,"name":"Burundi"},{"id":30,"name":"Cabo Verde"},{"id":31,"name":"Cambodia"},{"id":32,"name":"Cameroon"},{"id":33,"name":"Canada"},{"id":34,"name":"Central Afr"},{"id":35,"name":"Chad"},{"id":36,"name":"Chile"},{"id":37,"name":"China"},{"id":38,"name":"Colombia"},{"id":39,"name":"Comoros"},{"id":40,"name":"Democratic Republic of the Congo"},{"id":41,"name":"Costa Rica"},{"id":42,"name":"Côte d’Ivoire"},{"id":43,"name":"Croatia"},{"id":44,"name":"Cuba"},{"id":45,"name":"Cyprus"},{"id":46,"name":"Czech Republic"},{"id":47,"name":"Denmark"},{"id":48,"name":"Djibouti"},{"id":49,"name":"Dominica"},{"id":50,"name":"Dominican Republic"},{"id":51,"name":"Ecuador"},{"id":52,"name":"Egypt"},{"id":53,"name":"El Salvador"},{"id":54,"name":"Equatorial Guinea"},{"id":55,"name":"Eritrea"},{"id":56,"name":"Estonia"},{"id":57,"name":"Eswatini"},{"id":58,"name":"Ethiopia"},{"id":59,"name":"Fiji"},{"id":60,"name":"Finland"},{"id":61,"name":"France"},{"id":62,"name":"Gabon"},{"id":63,"name":"The Gambia"},{"id":64,"name":"Georgia"},{"id":65,"name":"Germany"},{"id":66,"name":"Ghana"},{"id":67,"name":"Greece"},{"id":68,"name":"Grenada"},{"id":69,"name":"Guatemala"},{"id":70,"name":"Guinea"},{"id":71,"name":"Guinea-Bissau"},{"id":72,"name":"Guyana"},{"id":73,"name":"Haiti"},{"id":74,"name":"Honduras"},{"id":75,"name":"Hungary"},{"id":76,"name":"Iceland"},{"id":77,"name":"India"},{"id":78,"name":"Indonesia"},{"id":79,"name":"Iran"},{"id":80,"name":"Iraq"},{"id":81,"name":"Ireland"},{"id":82,"name":"Israel"},{"id":83,"name":"Italy"},{"id":84,"name":"Jamaica"},{"id":85,"name":"Japan"},{"id":86,"name":"Jordan"},{"id":87,"name":"Kazakhstan"},{"id":88,"name":"Kenya"},{"id":89,"name":"Kiribati"},{"id":90,"name":"Korea, North"},{"id":91,"name":"Korea, South"},{"id":92,"name":"Kosovo"},{"id":93,"name":"Kuwait"},{"id":94,"name":"Kyrgyzstan"},{"id":95,"name":"Laos"},{"id":96,"name":"Latvia"},{"id":97,"name":"Lebanon"},{"id":98,"name":"Lesotho"},{"id":99,"name":"Liberia"},{"id":100,"name":"Libya"},{"id":101,"name":"Liechtenstein"},{"id":102,"name":"Lithuania"},{"id":103,"name":"Luxembourg"},{"id":104,"name":"Madagascar"},{"id":105,"name":"Malawi"},{"id":106,"name":"Malaysia"},{"id":107,"name":"Maldives"},{"id":108,"name":"Mali"},{"id":109,"name":"Malta"},{"id":110,"name":"Marshall Islands"},{"id":111,"name":"Mauritania"},{"id":112,"name":"Mauritius"},{"id":113,"name":"Mexico"},{"id":114,"name":"Moldova"},{"id":115,"name":"Monaco"},{"id":116,"name":"Mongolia"},{"id":117,"name":"Montenegro"},{"id":118,"name":"Morocco"},{"id":119,"name":"Mozambique"},{"id":120,"name":"Namibia"},{"id":121,"name":"Nauru"},{"id":122,"name":"Nepal"},{"id":123,"name":"Netherlands"},{"id":124,"name":"New Zealand"},{"id":125,"name":"Nicaragua"},{"id":126,"name":"Niger"},{"id":127,"name":"Nigeria"},{"id":128,"name":"North Macedonia"},{"id":129,"name":"Norway"},{"id":130,"name":"Oman"},{"id":131,"name":"Pakistan"},{"id":132,"name":"Palau"},{"id":133,"name":"Panama"},{"id":134,"name":"Papua New Guinea"},{"id":135,"name":"Paraguay"},{"id":136,"name":"Peru"},{"id":137,"name":"Philippines"},{"id":138,"name":"Poland"},{"id":139,"name":"Portugal"},{"id":140,"name":"Qatar"},{"id":141,"name":"Romania"},{"id":142,"name":"Russia"},{"id":143,"name":"Rwanda"},{"id":144,"name":"Saint Kitts Nevis"},{"id":145,"name":"Saint Lucia"},{"id":146,"name":"Samoa"},{"id":147,"name":"San Marino"},{"id":148,"name":"Saudi Arabia"},{"id":149,"name":"Senegal"},{"id":150,"name":"Serbia"},{"id":151,"name":"Seychelles"},{"id":152,"name":"Sierra Leone"},{"id":153,"name":"Singapore"},{"id":154,"name":"Slovakia"},{"id":155,"name":"Slovenia"},{"id":156,"name":"Solomon Islands"},{"id":157,"name":"Somalia"},{"id":158,"name":"South Africa"},{"id":159,"name":"Spain"},{"id":160,"name":"Sri Lanka"},{"id":161,"name":"Sudan"},{"id":162,"name":"Sudan, South"},{"id":163,"name":"Suriname"},{"id":164,"name":"Sweden"},{"id":165,"name":"Switzerland"},{"id":166,"name":"Syria"},{"id":167,"name":"Taiwan"},{"id":168,"name":"Tajikistan"},{"id":169,"name":"Tanzania"},{"id":170,"name":"Thailand"},{"id":171,"name":"Togo"},{"id":172,"name":"Tonga"},{"id":173,"name":"Trinidad and Tobago"},{"id":174,"name":"Tunisia"},{"id":175,"name":"Turkey"},{"id":176,"name":"Turkmenistan"},{"id":177,"name":"Tuvalu"},{"id":178,"name":"Uganda"},{"id":179,"name":"Ukraine"},{"id":180,"name":"United Arab Emirates"},{"id":181,"name":"United Kingdom"},{"id":182,"name":"United States"},{"id":183,"name":"Uruguay"},{"id":184,"name":"Uzbekistan"},{"id":185,"name":"Vanuatu"},{"id":186,"name":"Vatican City"},{"id":187,"name":"Venezuela"},{"id":188,"name":"Vietnam"},{"id":189,"name":"Yemen"},{"id":190,"name":"Zambia"},{"id":191,"name":"Zimbabwe"}]"""))
   )

  val usersScn = scenario("users") // A scenario is a chain of requests and pauses
    .exec(
      http("users")
      .get("/users")
//      .check(status.is(200), bodyString.is("""[{"id":93,"email":"iapetus@optonline.net","firstName":"Dustin","lastName":"Everett","countries":[{"id":61,"name":"France"}]},{"id":149,"email":"hermes@aol.com","firstName":"Kamryn","lastName":"Howell","countries":[{"id":61,"name":"France"},{"id":72,"name":"Guyana"},{"id":106,"name":"Malaysia"},{"id":111,"name":"Mauritania"},{"id":156,"name":"Solomon Islands"}]},{"id":203,"email":"stinson@yahoo.ca","firstName":"Bailee","lastName":"Austin","countries":[{"id":61,"name":"France"},{"id":135,"name":"Paraguay"},{"id":173,"name":"Trinidad and Tobago"}]},{"id":216,"email":"carcus@outlook.com","firstName":"Kassidy","lastName":"Hanna","countries":[{"id":30,"name":"Cabo Verde"},{"id":36,"name":"Chile"},{"id":61,"name":"France"},{"id":120,"name":"Namibia"},{"id":135,"name":"Paraguay"}]},{"id":223,"email":"mosses@yahoo.ca","firstName":"Hudson","lastName":"Gordon","countries":[{"id":61,"name":"France"},{"id":63,"name":"The Gambia"},{"id":99,"name":"Liberia"}]},{"id":244,"email":"amimojo@aol.com","firstName":"Andy","lastName":"Solis","countries":[{"id":30,"name":"Cabo Verde"},{"id":61,"name":"France"},{"id":165,"name":"Switzerland"},{"id":186,"name":"Vatican City"}]},{"id":298,"email":"munson@mac.com","firstName":"Sanaa","lastName":"Obrien","countries":[{"id":61,"name":"France"},{"id":181,"name":"United Kingdom"}]},{"id":401,"email":"wkrebs@outlook.com","firstName":"Kadin","lastName":"Page","countries":[{"id":23,"name":"Herzegovina"},{"id":61,"name":"France"},{"id":102,"name":"Lithuania"},{"id":168,"name":"Tajikistan"}]},{"id":509,"email":"fairbank@optonline.net","firstName":"Pranav","lastName":"Sloan","countries":[{"id":8,"name":"Armenia"},{"id":37,"name":"China"},{"id":52,"name":"Egypt"},{"id":61,"name":"France"},{"id":177,"name":"Tuvalu"}]},{"id":512,"email":"gward@icloud.com","firstName":"Anahi","lastName":"Fuller","countries":[{"id":61,"name":"France"},{"id":80,"name":"Iraq"},{"id":176,"name":"Turkmenistan"}]},{"id":523,"email":"webteam@comcast.net","firstName":"Jairo","lastName":"Mcclain","countries":[{"id":61,"name":"France"},{"id":145,"name":"Saint Lucia"},{"id":161,"name":"Sudan"}]},{"id":667,"email":"hedwig@yahoo.com","firstName":"Isabelle","lastName":"Becker","countries":[{"id":43,"name":"Croatia"},{"id":61,"name":"France"},{"id":100,"name":"Libya"},{"id":140,"name":"Qatar"},{"id":142,"name":"Russia"}]},{"id":692,"email":"lridener@comcast.net","firstName":"Karly","lastName":"Potts","countries":[{"id":58,"name":"Ethiopia"},{"id":61,"name":"France"},{"id":107,"name":"Maldives"},{"id":134,"name":"Papua New Guinea"}]},{"id":754,"email":"metzzo@optonline.net","firstName":"Adrian","lastName":"Brandt","countries":[{"id":61,"name":"France"},{"id":65,"name":"Germany"},{"id":87,"name":"Kazakhstan"},{"id":104,"name":"Madagascar"}]},{"id":831,"email":"jgmyers@sbcglobal.net","firstName":"Ray","lastName":"Ray","countries":[{"id":49,"name":"Dominica"},{"id":61,"name":"France"},{"id":130,"name":"Oman"},{"id":188,"name":"Vietnam"}]},{"id":842,"email":"onestab@gmail.com","firstName":"Elias","lastName":"Hebert","countries":[{"id":45,"name":"Cyprus"},{"id":61,"name":"France"},{"id":92,"name":"Kosovo"},{"id":123,"name":"Netherlands"},{"id":128,"name":"North Macedonia"}]},{"id":868,"email":"tokuhirom@mac.com","firstName":"Magdalena","lastName":"Kline","countries":[{"id":61,"name":"France"},{"id":105,"name":"Malawi"}]},{"id":922,"email":"esasaki@outlook.com","firstName":"Nicolas","lastName":"Lutz","countries":[{"id":54,"name":"Equatorial Guinea"},{"id":61,"name":"France"},{"id":75,"name":"Hungary"},{"id":122,"name":"Nepal"}]},{"id":970,"email":"dwendlan@aol.com","firstName":"Marc","lastName":"Grimes","countries":[{"id":59,"name":"Fiji"},{"id":61,"name":"France"},{"id":156,"name":"Solomon Islands"},{"id":166,"name":"Syria"}]},{"id":1045,"email":"aglassis@me.com","firstName":"Liberty","lastName":"Fuentes","countries":[{"id":61,"name":"France"},{"id":98,"name":"Lesotho"},{"id":123,"name":"Netherlands"},{"id":124,"name":"New Zealand"},{"id":149,"name":"Senegal"}]}]"""))
    )

    setUp (
    helloScn.inject(
      //      nothingFor(4 seconds), // 1
//      atOnceUsers(1), // 2
      //      rampUsers(10000) during (30 seconds), // 3
      //      constantUsersPerSec(20) during (15 seconds), // 4
      //      constantUsersPerSec(20) during (15 seconds) randomized, // 5
      //      rampUsersPerSec(100) to 1000 during (1 minutes), // 6
      //      rampUsersPerSec(10) to 20 during (10 minutes) randomized, // 7
      //      splitUsers(1000) into (rampUsers(10) over (10 seconds)) separatedBy (10 seconds), // 8
      //      splitUsers(1000) into (rampUsers(10) over (10 seconds)) separatedBy atOnceUsers(30), // 9
            heavisideUsers(10000) during (30 seconds) // 10
    ),
      computeScn.inject(heavisideUsers(10000) during (30 seconds)),
      countriesScn.inject(heavisideUsers(10000) during (30 seconds)),
      usersScn.inject(heavisideUsers(10000) during (30 seconds)),
  )
//      .throttle(
//    reachRps(3000) in (10 seconds),
//    holdFor(1 minute),
    //        jumpToRps(50),
    //        holdFor(2 hours)
//  )
//      .maxDuration(1 minutes)
      .protocols(httpProtocol)
}
