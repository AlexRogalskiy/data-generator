import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.{Arbitrary, Gen}
import org.scalatest.matchers.should.Matchers
//import org.scalatest.prop.PropertyChecks
//import org.scalatest.{FunSuite, Matchers}

import java.io.{File, PrintWriter}
import scala.collection.JavaConverters.{seqAsJavaListConverter, _}

class BagItemGeneratorClass
//  extends FunSuite with Matchers with PropertyChecks
{

//  test("Bag test") {
//    import BagTestGenerator2._
//    implicit val arbitraryEntityType: Arbitrary[Bag] = Arbitrary(genBag)
//
//    forAll { (bags: Seq[Bag]) =>
//      bags
//        .foreach(b => {
//          val str = avroToGson(b)
//
//          val fileName = s"test_entity_${b.getBaseInfo.getCreateDateTime.replaceAll("(:)|(-)|( )", "_")}.json"
//
//          val pw = new PrintWriter(new File(s"model-rtm30e/src/test/resources/output/$fileName"))
//          Using.using(pw) { p =>
//            p.write(str)
//          }
//        }
//        )
//    }
//  }

  case class Payment(
      user_id: Int,
      pay_doc_type: String,
      visa: String,
      pay_doc_num: Int,
      account: String,
      phone: String,
      billing_period: String,
      pay_date: String,
      sum: Double
  )

  implicit val genPayment: Gen[Payment] = for {
    user_id        <- arbitrary[Int]
    pay_doc_type   <- arbitrary[String]
    visa           <- arbitrary[String]
    pay_doc_num    <- arbitrary[Int]
    account        <- arbitrary[String]
    phone          <- arbitrary[String]
    billing_period <- arbitrary[String]
    pay_date       <- arbitrary[String]
    sum            <- arbitrary[Double]
  } yield Payment(
    user_id,
    pay_doc_type,
    visa,
    pay_doc_num,
    account,
    phone,
    billing_period,
    pay_date,
    sum
  )

  case class Traffic(
      user_id : Int,
      timestamp: Int,
      device_id: String,
      device_ip_addr: String,
      bytes_sent: Int,
      bytes_received: Int
  )

  implicit val genTraffic: Gen[Traffic] = for {
    user_id <- arbitrary[Int]
    timestamp <- arbitrary[Int]
    device_id <- arbitrary[String]
    device_ip_addr <- arbitrary[String]
    bytes_sent <- arbitrary[Int]
    bytes_received <- arbitrary[Int]
  } yield  Traffic(
    user_id,
  timestamp,
  device_id,
  device_ip_addr,
  bytes_sent,
  bytes_received
  )


  case class Billing(
      user_id: Int,
      billing_period: String,
      service: String,
      tariff: String,
      sum: String,
      created_at: String
  )

  case class Issue(
      user_id: String,
      start_time: String,
      end_time: String,
      title: String,
      description: String,
      service: String
  )

}

//Платежи
//Имя поля Тип поля Описание Пример
//  user_id bigint Идентификатор пользователя 1500023
//pay_doc_type varchar Тип платежного документа Кредитная карта
//Visa (на сайте)
//pay_doc_num bigint Номер платежа (уникален в
//  рамках каждого pay_doc_type)
//3485900052
//account varchar Лицевой счет клиента ФЛ-18709262
//phone varchar Телефонный номер клиента 79234567890
//billing_period varchar Период оплаты 2020-12
//pay_date varchar Дата оплаты 2021-01-10 14:52:12
//sum float Сумма платежа в рублях 101.50

//Трафик
//Имя поля Тип поля Описание Пример
//  user_id bigint Идентификатор пользователя 1500023
//timestamp bigint Время регистрации события в
//  миллисекундах
//1581177460000
//device_id varchar Серийный номер
//  пользовательского устройства
//  AN96763S43
//device_ip_addr varchar IP-адрес пользовательского
//  устройства
//172.16.3.82
//bytes_sent bigint Объем исходящего трафика 0
//bytes_received bigint Объем входящего трафика 67330
//Имя

//Начисления
//Имя поля Тип поля Описание Пример
//  user_id bigint Идентификатор пользователя 1500023
//billing_period varchar Период оплаты 2020-12
//service varchar Услуга Домашний
//  интернет
//tariff varchar Тариф Выгодный 500
//sum float Сумма начислений 110.0
//created_at datetime Дата начисления 2021-01-10 14:52:12

//Обращения
//Имя поля Тип поля Описание Пример
//  user_id number Идентификатор пользователя 1500023
//start_time datetime Дата открытия обращения 2021-01-10 14:52:12
//end_time datetime Дата закрытия обращения null
//title varchar Тема обращения Нет интернета
//  description text Описание Нет интернета два
//  дня, роутер
//перезагружал
//service varchar Услуга Домашний
//  интернет
