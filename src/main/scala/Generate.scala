import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.Gen


import java.io.{File, IOException, PrintWriter}
import java.text.SimpleDateFormat
import java.util.{Calendar, Date}
import scala.util.Using

final case class Generate(c1: Int, c2: Double, c3: Option[String])

object Generate {

  object RawDataGenerator {

    implicit val genUserId: Gen[Int] = Gen.oneOf(10010, 10020, 10030, 10040, 10050, 10060, 10070, 10080, 10090, 10100, 10110, 10120, 10130, 10140, 10150, 10160, 10170, 10180, 10190, 10200, 10210,
      10220, 10230, 10240, 10250, 10260, 10270, 10280, 10290, 10300, 10310, 10320, 10330, 10340, 10350, 10360, 10370, 10380, 10390, 10400, 10410, 10420, 10430, 10440, 10450, 10460, 10470, 10480,
      10490, 10500, 10510, 10520, 10530, 10540, 10550, 10560, 10570, 10580, 10590, 10600, 10610, 10620, 10630, 10640, 10650, 10660, 10670, 10680, 10690, 10700, 10710, 10720, 10730, 10740, 10750,
      10760, 10770, 10780, 10790, 10800, 10810, 10820, 10830, 10840, 10850, 10860, 10870, 10880, 10890, 10900, 10910, 10920, 10930, 10940, 10950, 10960, 10970, 10980, 10990, 11000, 11010, 11020,
      11030, 11040, 11050, 11060, 11070, 11080, 11090, 11100, 11110, 11120, 11130, 11140, 11150, 11160, 11170, 11180, 11190, 11200, 11210, 11220, 11230)

    implicit val genDeviceId: Gen[String] = Gen.oneOf("d001", "d002", "d003", "d004", "d005", "d006", "d007", "d008", "d009")

    implicit val genVisaType: Gen[String] = Gen.oneOf("VISA", "MASTER", "MIR")

    implicit val genPhoneNumber: Gen[Long] = Gen.oneOf(9010000000L to 9019999999L)

    implicit val genTransType: Gen[Int] = Gen.oneOf(1 to 5)

    implicit val genPositiveInt: Gen[Int] = Gen.oneOf(1 to 50000)

    implicit val genString: Gen[String] =
      Gen.listOfN[Char](14, Gen.alphaChar).map(_.mkString).filter(x => x.nonEmpty && x != null)

    def genDateWithoutFormat(sdf: SimpleDateFormat) = {
      val now      = new Date
      val calendar = Calendar.getInstance()
      calendar.set(2013, 0, 1) //Jan 1 2013

      Iterator
        .continually {
          val d = calendar.getTime
          calendar.add(Calendar.DAY_OF_YEAR, 1)
          sdf.format(d)
        }
        .takeWhile(_ => calendar.getTimeInMillis <= now.getTime)
        .toSeq
    }
    val sf                                  = new SimpleDateFormat("yyyy-MM-dd")
    val dates                               = genDateWithoutFormat(sf)
    implicit val genStringDate: Gen[String] = Gen.oneOf(dates)

    val sfTime                                  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val times                                   = genDateWithoutFormat(sfTime)
    implicit val genStringDateTime: Gen[String] = Gen.oneOf(times)

    val sfPeriod                        = new SimpleDateFormat("yyyy-MM")
    val period                          = genDateWithoutFormat(sfPeriod)
    implicit val genPeriod: Gen[String] = Gen.oneOf(period)

    implicit val genPayment: Gen[Payment] = for {
      user_id        <- genUserId
      pay_doc_type   <- genVisaType
      pay_doc_num    <- genPositiveInt
      account        <- genPositiveInt
      phone          <- genPhoneNumber
      billing_period <- genPeriod
      pay_date       <- genStringDate
      sum            <- genPositiveInt
      accountNumber = s"FL-$account"
      phoneNumber   = s"+7$phone"
    } yield Payment(
      user_id,
      pay_doc_type,
      pay_doc_num,
      accountNumber,
      phoneNumber,
      billing_period,
      pay_date,
      sum.toDouble
    )

    val genListOfPayments: Gen[scala.List[Payment]] =
      Gen.listOfN(10000, genPayment)

    case class Payment(
        user_id: Int,
        pay_doc_type: String,
        pay_doc_num: Int,
        account: String,
        phone: String,
        billing_period: String,
        pay_date: String,
        sum: Double
    )

    def genDateWithoutFormat2() = {
      val now      = new Date
      val calendar = Calendar.getInstance()
      calendar.set(2013, 0, 1) //Jan 1 2013

      Iterator
        .continually {
          calendar.add(Calendar.DAY_OF_YEAR, 1)
          calendar.getTimeInMillis
        }
        .takeWhile(_ => calendar.getTimeInMillis <= now.getTime)
        .toSeq
    }

    val dates2: Seq[Long] = genDateWithoutFormat2()
    //implicit val genDateTimeStamp: Gen[Long] = Gen.calendar.map(d => d.getTimeInMillis)
    implicit val genDateTimeStamp: Gen[Long] = Gen.oneOf(dates2)

    implicit val genIP: Gen[Int] = Gen.oneOf(1 to 192)

    implicit val genTraffic: Gen[Traffic] = for {
      user_id   <- genUserId
      timestamp <- genDateTimeStamp
      device_id <- genDeviceId
      ip1       <- genIP
      ip2       <- genIP
      ip3       <- genIP
      ip4       <- genIP
      device_ip_addr = s"$ip1.$ip2.$ip3.$ip4"
      bytes_sent     <- genPositiveInt
      bytes_received <- genPositiveInt
    } yield Traffic(user_id, timestamp, device_id, device_ip_addr, bytes_sent, bytes_received)

    val genListOfTraffic: Gen[scala.List[Traffic]] =
      Gen.listOfN(10000, genTraffic)

    case class Traffic(
        user_id: Int,
        timestamp: Long,
        device_id: String,
        device_ip_addr: String,
        bytes_sent: Int,
        bytes_received: Int
    )

    //Трафик
    //Имя поля Тип поля Описание Пример
    //  user_id bigint Идентификатор пользователя 1500023
    // timestamp bigint Время регистрации события в миллисекундах 1581177460000
    // device_id varchar Серийный номер пользовательского устройства AN96763S43
    //device_ip_addr varchar IP-адрес пользовательского устройства 172.16.3.82
    //bytes_sent bigint Объем исходящего трафика 0
    //bytes_received bigint Объем входящего трафика 67330
    //Имя

    implicit val genTariff: Gen[String] = Gen.oneOf("Gigabyte", "Megabyte", "Maxi", "Mini")

    implicit val genBilling: Gen[Billing] = for {
      user_id        <- genUserId
      billing_period <- genPeriod
      service        <- genString
      tariff         <- genTariff
      sum            <- genPositiveInt
      created_at     <- genStringDate
    } yield Billing(user_id, billing_period, service, tariff, sum.toString, created_at)

    val genListOfBilling: Gen[scala.List[Billing]] =
      Gen.listOfN(10000, genBilling)

    case class Billing(
        user_id: Int,
        billing_period: String,
        service: String,
        tariff: String,
        sum: String,
        created_at: String
    )

    //Начисления
    //Имя поля Тип поля Описание Пример
    // user_id bigint Идентификатор пользователя 1500023
    // billing_period varchar Период оплаты 2020-12
    // service varchar Услуга Домашний интернет
    // tariff varchar Тариф Выгодный 500
    // sum float Сумма начислений 110.0
    // created_at datetime Дата начисления 2021-01-10 14:52:12
    implicit val genService: Gen[String] = Gen.oneOf("Connect", "Disconnect", "Setup Environment")

    implicit val genIssue: Gen[Issue] = for {
      user_id     <- genUserId
      start_time  <- genStringDateTime
      end_time    <- genStringDateTime
      title       <- genString
      description <- genString
      service     <- genService
    } yield Issue(
      user_id.toString,
      start_time,
      end_time,
      title,
      description,
      service
    )

    val genListOfIssue: Gen[scala.List[Issue]] =
      Gen.listOfN(10000, genIssue)

    //Обращения
    //Имя поля Тип поля Описание Пример
    // user_id number Идентификатор пользователя 1500023
    // start_time datetime Дата открытия обращения 2021-01-10 14:52:12
    // end_time datetime Дата закрытия обращения null
    // title varchar Тема обращения Нет интернета
    // description text Описание Нет интернета два дня, роутер перезагружал
    // service varchar Услуга Домашний интернет

    case class Issue(
        user_id: String,
        start_time: String,
        end_time: String,
        title: String,
        description: String,
        service: String
    )

  }

  @throws[IOException]
  def objectToJson[A <: AnyRef](data: A, formats: org.json4s.Formats)(implicit manifest: Manifest[A]): String =
  //uses json4s because GSON cannot serialize Option properly
    write(data)(formats)

  @throws[IOException]
  def objectToJson[A <: AnyRef](data: A)(implicit manifest: Manifest[A]): String = {
    //uses json4s because GSON cannot serialize Option properly
    import org.json4s.DefaultFormats
    objectToJson(data, DefaultFormats)(manifest)
  }


  def collectAndWriteFile[T <: AnyRef](payment: List[T], fileName: String)(implicit manifest: Manifest[T]): Unit = {
    val paymentText = payment
      .foldLeft("") { case (acc, el) =>
        acc + objectToJson(el) + "\n"
      }

    val pw = new PrintWriter(new File(s"model-rtm30e/src/test/resources/payment/$fileName"))
    Using(pw) { p => p.write(paymentText) }
  }

  def main(args: Array[String]): Unit = {
    import RawDataGenerator._

    val payment = genListOfPayments.sample.get
    collectAndWriteFile(payment, "payment_total.json")

    val traffic = genListOfTraffic.sample.get
    collectAndWriteFile(traffic, "traffic_total.json")

    val billing = genListOfTraffic.sample.get
    collectAndWriteFile(billing, "billing_total.json")

    val issue = genListOfIssue.sample.get
    collectAndWriteFile(issue, "issue_total.json")
  }

//  def writeFile[T](text: String, fileName: String): Unit = {
//
//    val pw = new PrintWriter(new File(s"model-rtm30e/src/test/resources/payment/$fileName"))
//    Using.using(pw) { p => p.write(text) }
//  }
//
//
//
//  def main(args: Array[String]): Unit = {
//    import RawDataGenerator._
//
//    val payment = genListOfPayments.sample.get
//    val paymentText = payment
//      .foldLeft("") {
//        case (acc, el) => acc + objectToJson(el) + "\n"
//      }
//    val fileName = s"payment_total.json"
//    writeFile(paymentText, fileName)
//
//
//
//    val traffic = genListOfTraffic.sample.get
//    traffic.foldLeft("")
//    val fileNameTraffic = s"traffic_total.json"
//    writeFile(traffic, fileName)
//
//

}
