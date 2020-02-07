package com.stepasha.keyconservation

import com.stepasha.keyconservation.model.User
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.robolectric.android.controller.ActivityController

@RunWith(JUnit4::class)
public class UnitTests {


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

}
