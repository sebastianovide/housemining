import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

class LandRegistryPricePaidDataSpec extends FlatSpec with ShouldMatchers {
  "paidDatas of empty constructor" should "have zero elements" in {
    (new LandRegistryPricePaidData()).paidDatas.isEmpty should be === true
  }

  "paidDatas of constructor with one element" should "have elements" in {
    val paidDataItem = new LandRegistryPricePaidDataItem("1",1)
    (new LandRegistryPricePaidData(List(paidDataItem))).paidDatas.isEmpty shouldBe false
  }

  "merge with an empty list" should "have zero elements" in {
    (new LandRegistryPricePaidData()).merge(List()).paidDatas.isEmpty shouldBe true
  }

  "merge a list of one element" should "return a no empty map" in {
    val paidDataItem = new LandRegistryPricePaidDataItem("1",1)
    new LandRegistryPricePaidData().merge(List(paidDataItem)).paidDatas.isEmpty shouldBe false
  }

  "merge a list of one element with the same" should "return the same list" in {
    val paidDataItem = new LandRegistryPricePaidDataItem("1",1)
    val paidData = new LandRegistryPricePaidData(List(paidDataItem))
      
    paidData.merge(List(paidDataItem)).paidDatas should equal (paidData.paidDatas)
  }
  
  "merge a list of one element with a different list" should "return the two list added" in {
    val list1 = List(new LandRegistryPricePaidDataItem("1",1))
    val list2 = List(new LandRegistryPricePaidDataItem("2",2))
    val listAdd = list1:::list2

    val paidData1 = new LandRegistryPricePaidData(list1)
    val paidData2 = paidData1.merge(list2)
    val paidDataAdd = new LandRegistryPricePaidData(listAdd)
        
    paidData2.paidDatas should equal (paidDataAdd.paidDatas)      
  }
  
  "merge" should "update existing values" in {
    val el1 = new LandRegistryPricePaidDataItem("1",1)
    val el2 = new LandRegistryPricePaidDataItem("2",2)
    val el3 = new LandRegistryPricePaidDataItem("3",2)
    val el1new = new LandRegistryPricePaidDataItem("1",10)

    val list1 = List(el1, el2)
    val list2 = List(el3, el1new)
    val listAdd = List(el1new, el2, el3)

    val paidData1 = new LandRegistryPricePaidData(list1)
    val paidData2 = paidData1.merge(list2)
    val paidDataAdd = new LandRegistryPricePaidData(listAdd)
        
    paidData2.paidDatas should equal (paidDataAdd.paidDatas)      
  }
  
  "merge" should "delete a value" in {
    val el1 = new LandRegistryPricePaidDataItem("1",1)
    val el2 = new LandRegistryPricePaidDataItem("2",2)
    val el3 = new LandRegistryPricePaidDataItem("3",2)
    val el1new = new LandRegistryPricePaidDataItem("1",10, 'D')

    val list1 = List(el1, el2)
    val list2 = List(el3, el1new)
    val listExpected = List(el2, el3)

    val paidData1 = new LandRegistryPricePaidData(list1)
    val paidData2 = paidData1.merge(list2)
    val paidDataExpected = new LandRegistryPricePaidData(listExpected)
        
    paidData2.paidDatas should equal (paidDataExpected.paidDatas) 
  }

  "merge" should "return the right values" in {
    val el11 = new LandRegistryPricePaidDataItem("1",11)
    val el12 = new LandRegistryPricePaidDataItem("2",12)
    val el13 = new LandRegistryPricePaidDataItem("3",13)

    val el21 = new LandRegistryPricePaidDataItem("1",21)
    val el22 = new LandRegistryPricePaidDataItem("2",22, 'D')
    val el23 = new LandRegistryPricePaidDataItem("4",24)

    
    val list1 = List(el11, el12, el13)
    val list2 = List(el21, el22, el23)
    val listExpected = List(el21, el13, el23)

    val paidData1 = new LandRegistryPricePaidData(list1)
    val paidData2 = paidData1.merge(list2)
    val paidDataExpected = new LandRegistryPricePaidData(listExpected)
    

//    info(listAdd + "")
    
    paidData2.paidDatas should equal (paidDataExpected.paidDatas) 
  }  
  
}