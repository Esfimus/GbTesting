package com.esfimus.gbtesting

import com.esfimus.gbtesting.domain.EmailValidator
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class EmailValidatorTest {

    private val emailValidator = EmailValidator()

    @Test
    fun emailValidator_EmptyEmail_False() {
        assertFalse(emailValidator.isValid(null))
        assertFalse(emailValidator.isValid(""))
    }

    @Test
    fun emailValidator_OnlyWhitespace_False() {
        assertFalse(emailValidator.isValid(" "))
        assertFalse(emailValidator.isValid("    "))
    }

    @Test
    fun emailValidator_NoAtSign_False() {
        assertFalse(emailValidator.isValid("nameemail.com"))
        assertFalse(emailValidator.isValid("nameemailcom"))
    }

    @Test
    fun emailValidator_Name_False() {
        assertFalse(emailValidator.isValid("@email.com"))
        assertFalse(emailValidator.isValid("\"@email.com"))
        assertFalse(emailValidator.isValid("?@email.com"))
    }

    @Test
    fun emailValidator_SubDomain_False() {
        assertFalse(emailValidator.isValid("name@.com"))
        assertFalse(emailValidator.isValid("name@?.com"))
        assertFalse(emailValidator.isValid("name@com"))
    }

    @Test
    fun emailValidator_DomainZone_False() {
        assertFalse(emailValidator.isValid("name@email."))
        assertFalse(emailValidator.isValid("name@email.="))
        assertFalse(emailValidator.isValid("name@email.comcom"))
        assertFalse(emailValidator.isValid("name@email.c"))
    }

    @Test
    fun emailValidator_Dot_False() {
        assertFalse(emailValidator.isValid("name@email..com"))
        assertFalse(emailValidator.isValid("name@email,com"))
        assertFalse(emailValidator.isValid("name@email?com"))
        assertFalse(emailValidator.isValid("name@email.co..com"))
        assertFalse(emailValidator.isValid("name@email.co,com"))
    }

    @Test
    fun emailValidator_Correct_True() {
        assertTrue(emailValidator.isValid(CORRECT_EMAIL))
        assertTrue(emailValidator.isValid("name@e-mail.com"))
        assertTrue(emailValidator.isValid("name@e-mail-mail-mail.com"))
        assertTrue(emailValidator.isValid("name@email.com.com"))
    }

    @Test
    fun emailValidator_Email_NotNull() {
        assertNotNull(emailValidator.getEmailCharCount(""))
    }

    @Test
    fun emailValidator_EmailLength_Equals() {
        assertEquals(emailValidator.getEmailCharCount("name@email.com"), 14)
        assertEquals(emailValidator.getEmailCharCount(" name@email.com "), 14)
        assertEquals(emailValidator.getEmailCharCount("n@e.co"), 6)
    }

    @Test
    fun emailValidator_EmailLength_NotEquals() {
        assertNotEquals(emailValidator.getEmailCharCount("name@email.com"), 13)
        assertNotEquals(emailValidator.getEmailCharCount(" name@email.com "), 16)
        assertNotEquals(emailValidator.getEmailCharCount("   n@e.co"), 9)
    }

}