package com.stepasha.keyconservation.services;

//mport com.stepasha.keyconservation.KeyConservationApplication;
//mport com.stepasha.keyconservation.models.Campaigns;
//mport com.stepasha.keyconservation.services.CampaignsService;
//mport org.junit.After;
//mport org.junit.Before;
//mport org.junit.FixMethodOrder;
//mport org.junit.Test;
//mport org.junit.runner.RunWith;
//mport org.junit.runners.MethodSorters;
//mport org.mockito.MockitoAnnotations;
//mport org.springframework.beans.factory.annotation.Autowired;
//mport org.springframework.boot.test.context.SpringBootTest;
//mport org.springframework.test.context.junit4.SpringRunner;

//mport java.util.ArrayList;

//mport static junit.framework.TestCase.assertEquals;

//RunWith(SpringRunner.class)
//SpringBootTest(classes = KeyConservationApplication.class)
//FixMethodOrder(MethodSorters.NAME_ASCENDING)
//ublic class CampaignServiceImplUnitTest {

//   @Autowired
//   private CampaignsService campaignsService;

//   @Before
//   public void AsetUp() throws Exception {
//       MockitoAnnotations.initMocks(this);
//   }
//   @After
//   public void BtearDown() throws Exception {
//   }


//   @Test
//   public void CfindAllZoos() {
//       assertEquals(5, campaignsService.findAll().size());
//   }

//   @Test
//   public void DfindZooById() {
//       assertEquals("Gladys Porter Zoo", campaignsService.getCampaignsById(1).getEvent_name());
//   }



//   @Test
//   public void FdeleteZoo() {
//       campaignsService.delete(1);
//       assertEquals(4, campaignsService.findAll().size());
//   }

//   //  @Test
//   //  public void GsaveZoo() {
//   //  }

//   @Test
//   public void HupdateZoo() {
//       ArrayList<Campaigns> thisCampaigns = new ArrayList<>();
//       ArrayList<Campaigns> thisAnimal = new ArrayList<>();
//       Campaigns camp1 = new Campaigns(10, "Recycle", "NEW", "Denver",
//               20.0001f,
//               -145.000003f,
//               null,
//               "image",
//               "title",
//               "description",
//               null);
//       camp1.setEventid(1);

//       Campaigns updateZoo1 = campaignsService.update(camp1, 3);

//       assertEquals("title", updateZoo1.getEvent_name());
//   }

//   // @Test
//   // public void IdeleteZooAnimal() {
//   // }
///
//   // @Test
//   // public void JaddZooAnimal() {
//   // }
//