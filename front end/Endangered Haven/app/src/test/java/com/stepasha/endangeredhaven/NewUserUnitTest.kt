package com.stepasha.endangeredhaven

import com.stepasha.endangeredhaven.model.Neweruser
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.robolectric.android.controller.ActivityController

@RunWith(JUnit4::class)
class NewUserUnitTest {

    /*   val testUsername = "stepasha2006"
        newUserModel = Neweruser(
          private val ALLOWED_PROFILEPIC   =  "none",
          private val ALLOWED_USERNAME   =  "stepasha2006",
          private val ALLOWED_PASSWORD   =  "password",
          private val ALLOWED_EMAIL   =  "gmail@gmail.com",
          private val ALLOWED_FACEBOOK   =  "facebook",
          private val ALLOWED_TWITTER   =  "twitter",
          private val ALLOWED_INSTAGRAM   =  "instagram",
          private val ALLOWED_FIRSTNAME   =  "Albert",
          private val ALLOWED_LASTNAME   =  "Yakubov",
          private val ALLOWED_POSITION   =  true,
          private val ALLOWED_MINIBIO   =  "I am nice",
          private val ALLOWED_SPECIES   =  "cats",
          private val ALLOWED_ISSUES   =  "Global Warming",
          private val ALLOWED_ABOUTUS   =  "Greener Earth",
          private val ALLOWED_LOCATION   =  "Denver",
          private val ALLOWED_LON   =  -120.004654,
          private val ALLOWED_LAT   =  39.0044)*/


    // ActivityController is a Robolectric class that drives the Activity lifecycle
    private var controller: ActivityController<MainActivity>? = null
    //initializing activity
    lateinit var activity: MainActivity

    //initializing notes model
    lateinit var newUserModel: Neweruser
    //actuals
    private val ALLOWED_PROFILEPIC   =  "none"
    private val ALLOWED_USERNAME   =  "stepasha2006"
    private val ALLOWED_PASSWORD   =  "password"
    private val ALLOWED_EMAIL   =  "gmail@gmail.com"
    private val ALLOWED_FACEBOOK   =  "facebook"
    private val ALLOWED_TWITTER   =  "twitter"
    private val ALLOWED_INSTAGRAM   =  "instagram"
    private val ALLOWED_FIRSTNAME   =  "Albert"
    private val ALLOWED_LASTNAME   =  "Yakubov"
    private val ALLOWED_POSITION   =  true
    private val ALLOWED_MINIBIO   =  "I am nice"
    private val ALLOWED_SPECIES   =  "cats"
    private val ALLOWED_ISSUES   =  "Global Warming"
    private val ALLOWED_ABOUTUS   =  "Greener Earth"
    private val ALLOWED_LOCATION   =  "Denver"
    private val ALLOWED_LON   =  -120.004654
    private val ALLOWED_LAT   =  39.0044



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


    //test if fragment loads
    @Test
    fun login_should_not_be_null() {
        val frag1 = LoginActivity()
        Assert.assertNotNull(frag1)
    }
    @Test
    fun instagramTest() {
        val testInstagram = "instagram"
        newUserModel = Neweruser(
            "none",
            "stepasha2006",
            "password",
            "gmail@gmail.com",
            "facebook",
            "twitter",
            "instagram",
            "Albert",
            "Yakubov",
            true,
            "I am nice",
            "cats",
            "Global Warming",
            "Greener Earth",
            "Denver",
            -120.004654,
            39.0044
        )

        junit.framework.Assert.assertEquals(testInstagram, newUserModel.instagram, ALLOWED_INSTAGRAM)
    }
    @Test
    fun profilepicTest() {
        val testPic = "none"
        newUserModel = Neweruser(
            "none",
            "stepasha2006",
            "password",
            "gmail@gmail.com",
            "facebook",
            "twitter",
            "instagram",
            "Albert",
            "Yakubov",
            true,
            "I am nice",
            "cats",
            "Global Warming",
            "Greener Earth",
            "Denver",
            -120.004654,
            39.0044
        )

        junit.framework.Assert.assertEquals(testPic, newUserModel.profilepicture, ALLOWED_PROFILEPIC)
    }
    @Test
    fun usernameTest() {
        val testUsername = "stepasha2006"
        newUserModel = Neweruser(
            "none",
            "stepasha2006",
            "password",
            "gmail@gmail.com",
            "facebook",
            "twitter",
            "instagram",
            "Albert",
            "Yakubov",
            true,
            "I am nice",
            "cats",
            "Global Warming",
            "Greener Earth",
            "Denver",
            -120.004654,
            39.0044
        )

        junit.framework.Assert.assertEquals(testUsername, newUserModel.username, ALLOWED_USERNAME)
    }
    @Test
    fun passwordTest() {
        val testPassword = "password"
        newUserModel = Neweruser(
            "none",
            "stepasha2006",
            "password",
            "gmail@gmail.com",
            "facebook",
            "twitter",
            "instagram",
            "Albert",
            "Yakubov",
            true,
            "I am nice",
            "cats",
            "Global Warming",
            "Greener Earth",
            "Denver",
            -120.004654,
            39.0044
        )

        junit.framework.Assert.assertEquals(testPassword, newUserModel.password, ALLOWED_PASSWORD)
    }
    @Test
    fun emailTest() {
        val testEmail = "gmail@gmail.com"
        newUserModel = Neweruser(
            "none",
            "stepasha2006",
            "password",
            "gmail@gmail.com",
            "facebook",
            "twitter",
            "instagram",
            "Albert",
            "Yakubov",
            true,
            "I am nice",
            "cats",
            "Global Warming",
            "Greener Earth",
            "Denver",
            -120.004654,
            39.0044
        )
        junit.framework.Assert.assertEquals(testEmail, newUserModel.primaryemail, ALLOWED_EMAIL)

    }
    @Test
    fun speciesTest() {

        val testSpecies = "Cats"
        newUserModel = Neweruser(
            "none",
            "stepasha2006",
            "password",
            "gmail@gmail.com",
            "facebook",
            "twitter",
            "instagram",
            "Albert",
            "Yakubov",
            true,
            "I am nice",
            "cats",
            "Global Warming",
            "Greener Earth",
            "Denver",
            -120.004654,
            39.0044
        )
        junit.framework.Assert.assertEquals(testSpecies, newUserModel.species, ALLOWED_SPECIES)

    }

    @Test
    fun facebookTest() {
//serves as key or a double sided scale
        val testFacebook = "facebook"
        newUserModel = Neweruser(
            "none",
            "stepasha2006",
            "password",
            "gmail@gmail.com",
            "facebook",
            "twitter",
            "instagram",
            "Albert",
            "Yakubov",
            true,
            "I am nice",
            "cats",
            "Global Warming",
            "Greener Earth",
            "Denver",
            -120.004654,
            39.0044
        )

        junit.framework.Assert.assertEquals(testFacebook, newUserModel.facebook, ALLOWED_FACEBOOK)

    }
    @Test
    fun twitterTest() {
//serves as key or a double sided scale
        val testTwitter = "twitter"
        newUserModel = Neweruser(
            "none",
            "stepasha2006",
            "password",
            "gmail@gmail.com",
            "facebook",
            "twitter",
            "instagram",
            "Albert",
            "Yakubov",
            true,
            "I am nice",
            "cats",
            "Global Warming",
            "Greener Earth",
            "Denver",
            -120.004654,
            39.0044
        )

        junit.framework.Assert.assertEquals(testTwitter, newUserModel.twitter, ALLOWED_TWITTER)

    }
    @Test
    fun firstnameTest() {
//serves as key or a double sided scale
        val testFirstname = "Albert"
        newUserModel = Neweruser(
            "none",
            "stepasha2006",
            "password",
            "gmail@gmail.com",
            "facebook",
            "twitter",
            "instagram",
            "Albert",
            "Yakubov",
            true,
            "I am nice",
            "cats",
            "Global Warming",
            "Greener Earth",
            "Denver",
            -120.004654,
            39.0044
        )

        junit.framework.Assert.assertEquals(testFirstname, newUserModel.firstname, ALLOWED_FIRSTNAME)

    }
    @Test
    fun lastnameTest() {
//serves as key or a double sided scale
        val testLastname = "Yakubov"
        newUserModel = Neweruser(
            "none",
            "stepasha2006",
            "password",
            "gmail@gmail.com",
            "facebook",
            "twitter",
            "instagram",
            "Albert",
            "Yakubov",
            true,
            "I am nice",
            "cats",
            "Global Warming",
            "Greener Earth",
            "Denver",
            -120.004654,
            39.0044
        )

        junit.framework.Assert.assertEquals(testLastname, newUserModel.lastname, ALLOWED_LASTNAME)

    }
    @Test
    fun issuesTest() {
//serves as key or a double sided scale
        val testIssues = "Global Warming"
        newUserModel = Neweruser(
            "none",
            "stepasha2006",
            "password",
            "gmail@gmail.com",
            "facebook",
            "twitter",
            "instagram",
            "Albert",
            "Yakubov",
            true,
            "I am nice",
            "cats",
            "Global Warming",
            "Greener Earth",
            "Denver",
            -120.004654,
            39.0044
        )

        junit.framework.Assert.assertEquals(testIssues, newUserModel.issues, ALLOWED_ISSUES)

    }
    @Test
    fun aboutusTest() {
//serves as key or a double sided scale
        val testAboutus = "Greener Earth"
        newUserModel = Neweruser(
            "none",
            "stepasha2006",
            "password",
            "gmail@gmail.com",
            "facebook",
            "twitter",
            "instagram",
            "Albert",
            "Yakubov",
            true,
            "I am nice",
            "cats",
            "Global Warming",
            "Greener Earth",
            "Denver",
            -120.004654,
            39.0044
        )

        junit.framework.Assert.assertEquals(testAboutus, newUserModel.about_us, ALLOWED_ABOUTUS)

    }
    @Test
    fun locationTest() {
//serves as key or a double sided scale
        val testLocation = "Denver"
        newUserModel = Neweruser(
            "none",
            "stepasha2006",
            "password",
            "gmail@gmail.com",
            "facebook",
            "twitter",
            "instagram",
            "Albert",
            "Yakubov",
            true,
            "I am nice",
            "cats",
            "Global Warming",
            "Greener Earth",
            "Denver",
            -120.004654,
            39.0044
        )
        junit.framework.Assert.assertEquals(testLocation, newUserModel.location, ALLOWED_LOCATION)

    }
    @Test
    fun positionTest() {
//serves as key or a double sided scale
        val testPosition = "true"
        newUserModel = Neweruser(
            "none",
            "stepasha2006",
            "password",
            "gmail@gmail.com",
            "facebook",
            "twitter",
            "instagram",
            "Albert",
            "Yakubov",
            true,
            "I am nice",
            "cats",
            "Global Warming",
            "Greener Earth",
            "Denver",
            -120.004654,
            39.0044
        )

        junit.framework.Assert.assertEquals(testPosition, newUserModel.position, ALLOWED_POSITION)

    }
    @Test
    fun minibioTest() {
//serves as key or a double sided scale
        val testMinibio = "I am nice"
        newUserModel = Neweruser(
            "none",
            "stepasha2006",
            "password",
            "gmail@gmail.com",
            "facebook",
            "twitter",
            "instagram",
            "Albert",
            "Yakubov",
            true,
            "I am nice",
            "cats",
            "Global Warming",
            "Greener Earth",
            "Denver",
            -120.004654,
            39.0044
        )

        junit.framework.Assert.assertEquals(testMinibio, newUserModel.mini_bio, ALLOWED_MINIBIO)

    }

    @Test
    fun latitudeTest() {
//serves as key or a double sided scale
        val testLat = "39.00452"
        newUserModel = Neweruser(
            "none",
            "stepasha2006",
            "password",
            "gmail@gmail.com",
            "facebook",
            "twitter",
            "instagram",
            "Albert",
            "Yakubov",
            true,
            "I am nice",
            "cats",
            "Global Warming",
            "Greener Earth",
            "Denver",
            39.0044,
            -120.004654
        )

        junit.framework.Assert.assertEquals(testLat, newUserModel.ulatitude, ALLOWED_LAT)

    }
    @Test
    fun longitudeTest() {
//serves as key or a double sided scale
        val testLon = "-120.004654"
        newUserModel = Neweruser(
            "none",
            "stepasha2006",
            "password",
            "gmail@gmail.com",
            "facebook",
            "twitter",
            "instagram",
            "Albert",
            "Yakubov",
            true,
            "I am nice",
            "cats",
            "Global Warming",
            "Greener Earth",
            "Denver",
            39.0044,
            -120.004654
        )

        junit.framework.Assert.assertEquals(testLon, newUserModel.ulongitude, ALLOWED_LON)

    }
}