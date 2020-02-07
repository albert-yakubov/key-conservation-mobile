package com.stepasha.keyconservation

import com.stepasha.keyconservation.model.UpdateUser
import com.stepasha.keyconservation.model.User
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.robolectric.android.controller.ActivityController

@RunWith(JUnit4::class)
public class UserUnitTest {


    // ActivityController is a Robolectric class that drives the Activity lifecycle
    private var controller: ActivityController<MainActivity>? = null
    //initializing activity
    lateinit var activity: MainActivity

    //initializing notes model
    lateinit var userModel: User
    //actuals
    private val ALLOWED_ID = 1L
    private val ALLOWED_USERNAME = "stepasha2006"
    private val ALLOWED_EMAIL = "gmail@gmail.com"
    private val ALLOWED_SPECIES = "cats"
    private val ALLOWED_FACEBOOK = "facebook"
    private val ALLOWED_TWITTER = "twitter"
    private val ALLOWED_FIRSTNAME = "Albert"
    private val ALLOWED_LASTNAME = "Yakubov"
    private val ALLOWED_POSITION = true
    private val ALLOWED_MINIBIO = "I am nice"
    private val ALLOWED_LOCATION = "Denver"
    private val ALLOWED_ABOUTUS = "Greener Earth"
    private val ALLOWED_ISSUES = "Global Warming"
    private val ALLOWED_LAT = 39.00452
    private val ALLOWED_LON = -120.004654


    //creates tests based on mock annotations


    @Before
    @Throws(Exception::class)
    fun setUp() {


        //set up activity
        activity = MainActivity()



    }


    @Test
    //checks if activity loads
    @Throws(java.lang.Exception::class)
    fun shouldNotBeNull() {
        Assert.assertNotNull(activity)


    }

    //test if activity 2 loads
    @Test
    fun creatingPost_ShouldNOT_be_Null() {
        // Given
        val postActivity = SplashActivity()
        // When
        // Then
        Assert.assertNotNull(postActivity)
    }
    //test if activity 2 loads
    @Test
    fun UPDATEuSER_ShouldNOT_be_Null() {
        // Given
        val updateUser = UpdateUser()
        // When
        // Then
        Assert.assertNotNull(updateUser)
    }
    //test if activity 2 loads
    @Test
    fun Connect_ShouldNOT_be_Null() {
        // Given
        val connectActivity = ConnectActivity()
        // When
        // Then
        Assert.assertNotNull(connectActivity)
    }
    //test if activity 2 loads
    @Test
    fun conserveRegister_ShouldNOT_be_Null() {
        // Given
        val conservationRegisterActivity = ConservationRegisterActivity()
        // When
        // Then
        Assert.assertNotNull(conservationRegisterActivity)
    }
    //test if activity 2 loads
    @Test
    fun loginScreen_ShouldNOT_be_Null() {
        // Given
        val loginScreenActivity = LoginScreenActivity()
        // When
        // Then
        Assert.assertNotNull(loginScreenActivity)
    }
    //test if activity 2 loads
    @Test
    fun profile_ShouldNOT_be_Null() {
        // Given
        val profileActivity = ProfileActivity()
        // When
        // Then
        Assert.assertNotNull(profileActivity)
    }
    //test if activity 2 loads
    @Test
    fun Maps_ShouldNOT_be_Null() {
        // Given
        val mapsActivity = MapsActivity()
        // When
        // Then
        Assert.assertNotNull(mapsActivity)
    }






    //test if fragment loads
    @Test
    fun login_should_not_be_null() {
        val frag1 = LoginActivity()
        Assert.assertNotNull(frag1)
    }
    @Test
    fun usernameTest() {
        val testUsername = "stepasha2006"
        userModel = User(0,
            1, "Note",
            "stepasha2006",
            "password",
            "gmail@gmail.com",
            "Albert",
            "Yakubov",
            true,
            "I am nice",
            "cats",
            "facebook",
            "instagram",
            "twitter",
            "Denver",
            39.00452,
            -120.004654,
            "Greener Earth",
            "Global Warming")

        junit.framework.Assert.assertEquals(testUsername, userModel.username, ALLOWED_USERNAME)

    }
    @Test
    fun emailTest() {
        val testEmail = "gmail@gmail.com"
        userModel = User(0,
            1, "Note",
            "stepasha2006",
            "password",
            "gmail@gmail.com",
            "Albert",
            "Yakubov",
            true,
            "I am nice",
            "cats",
            "facebook",
            "instagram",
            "twitter",
            "Denver",
            39.00452,
            -120.004654,
            "Greener Earth",
            "Global Warming")

        junit.framework.Assert.assertEquals(testEmail, userModel.primaryemail, ALLOWED_EMAIL)

    }
    @Test
    fun speciesTest() {

        val testSpecies = "Cats"
        userModel = User(0,
            1, "Note",
            "stepasha2006",
            "password",
            "gmail@gmail.com",
            "Albert",
            "Yakubov",
            true,
            "I am nice",
            "cats",
            "facebook",
            "instagram",
            "twitter",
            "Denver",
            39.00452,
            -120.004654,
            "Greener Earth",
            "Global Warming")

        junit.framework.Assert.assertEquals(testSpecies, userModel.species, ALLOWED_SPECIES)

    }
    @Test
    fun idTest() {
//serves as key or a double sided scale
        val testIdVal = "1"
        userModel = User(1,
            1, "Note",
            "stepasha2006",
            "password",
            "gmail@gmail.com",
            "Albert",
            "Yakubov",
            true,
            "I am nice",
            "cats",
            "facebook",
            "instagram",
            "twitter",
            "Denver",
            39.00452,
            -120.004654,
            "Greener Earth",
            "Global Warming")

        junit.framework.Assert.assertEquals(testIdVal, userModel.userid, ALLOWED_ID)

    }
    @Test
    fun facebookTest() {
//serves as key or a double sided scale
        val testFacebook = "facebook"
        userModel = User(1,
            1, "Note",
            "stepasha2006",
            "password",
            "gmail@gmail.com",
            "Albert",
            "Yakubov",
            true,
            "I am nice",
            "cats",
            "facebook",
            "instagram",
            "twitter",
            "Denver",
            39.00452,
            -120.004654,
            "Greener Earth",
            "Global Warming")

        junit.framework.Assert.assertEquals(testFacebook, userModel.facebook, ALLOWED_FACEBOOK)

    }
    @Test
    fun twitterTest() {
//serves as key or a double sided scale
        val testTwitter = "twitter"
        userModel = User(1,
            1, "Note",
            "stepasha2006",
            "password",
            "gmail@gmail.com",
            "Albert",
            "Yakubov",
            true,
            "I am nice",
            "cats",
            "facebook",
            "instagram",
            "twitter",
            "Denver",
            39.00452,
            -120.004654,
            "Greener Earth",
            "Global Warming")

        junit.framework.Assert.assertEquals(testTwitter, userModel.twitter, ALLOWED_TWITTER)

    }
    @Test
    fun firstnameTest() {
//serves as key or a double sided scale
        val testFirstname = "Albert"
        userModel = User(1,
            1, "Note",
            "stepasha2006",
            "password",
            "gmail@gmail.com",
            "Albert",
            "Yakubov",
            true,
            "I am nice",
            "cats",
            "facebook",
            "instagram",
            "twitter",
            "Denver",
            39.00452,
            -120.004654,
            "Greener Earth",
            "Global Warming")

        junit.framework.Assert.assertEquals(testFirstname, userModel.firstname, ALLOWED_FIRSTNAME)

    }
    @Test
    fun lastnameTest() {
//serves as key or a double sided scale
        val testLastname = "Yakubov"
        userModel = User(1,
            1, "Note",
            "stepasha2006",
            "password",
            "gmail@gmail.com",
            "Albert",
            "Yakubov",
            true,
            "I am nice",
            "cats",
            "facebook",
            "instagram",
            "twitter",
            "Denver",
            39.00452,
            -120.004654,
            "Greener Earth",
            "Global Warming")

        junit.framework.Assert.assertEquals(testLastname, userModel.lastname, ALLOWED_LASTNAME)

    }
    @Test
    fun issuesTest() {
//serves as key or a double sided scale
        val testIssues = "Global Warming"
        userModel = User(1,
            1, "Note",
            "stepasha2006",
            "password",
            "gmail@gmail.com",
            "Albert",
            "Yakubov",
            true,
            "I am nice",
            "cats",
            "facebook",
            "instagram",
            "twitter",
            "Denver",
            39.00452,
            -120.004654,
            "Greener Earth",
            "Global Warming")

        junit.framework.Assert.assertEquals(testIssues, userModel.issues, ALLOWED_ISSUES)

    }
    @Test
    fun aboutusTest() {
//serves as key or a double sided scale
        val testAboutus = "Greener Earth"
        userModel = User(1,
            1, "Note",
            "stepasha2006",
            "password",
            "gmail@gmail.com",
            "Albert",
            "Yakubov",
            true,
            "I am nice",
            "cats",
            "facebook",
            "instagram",
            "twitter",
            "Denver",
            39.00452,
            -120.004654,
            "Greener Earth",
            "Global Warming")

        junit.framework.Assert.assertEquals(testAboutus, userModel.about_us, ALLOWED_ABOUTUS)

    }
    @Test
    fun locationTest() {
//serves as key or a double sided scale
        val testLocation = "Denver"
        userModel = User(1,
            1, "Note",
            "stepasha2006",
            "password",
            "gmail@gmail.com",
            "Albert",
            "Yakubov",
            true,
            "I am nice",
            "cats",
            "facebook",
            "instagram",
            "twitter",
            "Denver",
            39.00452,
            -120.004654,
            "Greener Earth",
            "Global Warming")

        junit.framework.Assert.assertEquals(testLocation, userModel.location, ALLOWED_LOCATION)

    }
    @Test
    fun positionTest() {
//serves as key or a double sided scale
        val testPosition = "true"
        userModel = User(1,
            1, "Note",
            "stepasha2006",
            "password",
            "gmail@gmail.com",
            "Albert",
            "Yakubov",
            true,
            "I am nice",
            "cats",
            "facebook",
            "instagram",
            "twitter",
            "Denver",
            39.00452,
            -120.004654,
            "Greener Earth",
            "Global Warming")

        junit.framework.Assert.assertEquals(testPosition, userModel.position, ALLOWED_POSITION)

    }
    @Test
    fun minibioTest() {
//serves as key or a double sided scale
        val testMinibio = "I am nice"
        userModel = User(1,
            1, "Note",
            "stepasha2006",
            "password",
            "gmail@gmail.com",
            "Albert",
            "Yakubov",
            true,
            "I am nice",
            "cats",
            "facebook",
            "instagram",
            "twitter",
            "Denver",
            39.00452,
            -120.004654,
            "Greener Earth",
            "Global Warming")

        junit.framework.Assert.assertEquals(testMinibio, userModel.mini_bio, ALLOWED_MINIBIO)

    }

    @Test
    fun latitudeTest() {
//serves as key or a double sided scale
        val testLat = "39.00452"
        userModel = User(1,
            1, "Note",
            "stepasha2006",
            "password",
            "gmail@gmail.com",
            "Albert",
            "Yakubov",
            true,
            "I am nice",
            "cats",
            "facebook",
            "instagram",
            "twitter",
            "Denver",
            39.00452,
            -120.004654,
            "Greener Earth",
            "Global Warming")

        junit.framework.Assert.assertEquals(testLat, userModel.ulatitude, ALLOWED_LAT)

    }
    @Test
    fun longitudeTest() {
//serves as key or a double sided scale
        val testLon = "-120.004654"
        userModel = User(1,
            1, "Note",
            "stepasha2006",
            "password",
            "gmail@gmail.com",
            "Albert",
            "Yakubov",
            true,
            "I am nice",
            "cats",
            "facebook",
            "instagram",
            "twitter",
            "Denver",
            39.00452,
            -120.004654,
            "Greener Earth",
            "Global Warming")

        junit.framework.Assert.assertEquals(testLon, userModel.ulongitude, ALLOWED_LON)

    }


}
