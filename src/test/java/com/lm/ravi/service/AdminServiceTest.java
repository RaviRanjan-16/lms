package com.lm.ravi.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lm.ravi.entity.Admin;
import com.lm.ravi.repository.AdminRepository;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminService adminService;

    private Admin admin;
    private String adminEmail;
    private String adminPassword;
    private String invalidPassword;
    private String unknownEmail;

    @BeforeEach
    void setUp() {
        adminEmail = "admin@library.com";
        adminPassword = "admin123";
        invalidPassword = "wrongpassword";
        unknownEmail = "unknown@library.com";

        admin = new Admin(adminEmail, adminPassword);
    }

    // Test initializeAdmin method
    @Test
    void testInitializeAdmin_WhenAdminNotExists() {
        // Given: No admin exists in the DB
        when(adminRepository.findByEmail(adminEmail)).thenReturn(Optional.empty());

        // When: Method is called
        adminService.initializeAdmin();

        // Then: Verify the admin is saved
        verify(adminRepository, times(1)).save(any(Admin.class));
    }

    @Test
    void testInitializeAdmin_WhenAdminAlreadyExists() {
        // Given: Admin already exists in the DB
        when(adminRepository.findByEmail(adminEmail)).thenReturn(Optional.of(admin));

        // When: Method is called
        adminService.initializeAdmin();

        // Then: Verify that save is NOT called
        verify(adminRepository, never()).save(any(Admin.class));
    }

    // Test authenticateAdmin method
    @Test
    void testAuthenticateAdmin_ValidCredentials() {
        // Given: Admin exists with correct credentials
        when(adminRepository.findByEmail(adminEmail)).thenReturn(Optional.of(admin));

        // When: Authenticate with correct credentials
        boolean result = adminService.authenticateAdmin(adminEmail, adminPassword);

        // Then: Authentication should be successful
        assertTrue(result);
    }

    @Test
    void testAuthenticateAdmin_InvalidPassword() {
        // Given: Admin exists but with a different password
        when(adminRepository.findByEmail(adminEmail)).thenReturn(Optional.of(admin));

        // When: Authenticate with incorrect password
        boolean result = adminService.authenticateAdmin(adminEmail, invalidPassword);

        // Then: Authentication should fail
        assertFalse(result);
    }

    @Test
    void testAuthenticateAdmin_EmailNotFound() {
        // Given: Admin does not exist
        when(adminRepository.findByEmail(unknownEmail)).thenReturn(Optional.empty());

        // When: Authenticate with non-existing email
        boolean result = adminService.authenticateAdmin(unknownEmail, adminPassword);

        // Then: Authentication should fail
        assertFalse(result);
    }

    // Test getAdminByEmail method
    @Test
    void testGetAdminByEmail_AdminExists() {
        // Given: Admin exists in DB
        when(adminRepository.findByEmail(adminEmail)).thenReturn(Optional.of(admin));

        // When: Fetch admin by email
        Optional<Admin> foundAdmin = adminService.getAdminByEmail(adminEmail);

        // Then: Ensure correct admin is returned
        assertTrue(foundAdmin.isPresent());
        assertEquals(adminEmail, foundAdmin.get().getEmail());
    }

    @Test
    void testGetAdminByEmail_AdminNotExists() {
        // Given: No admin exists in DB
        when(adminRepository.findByEmail(unknownEmail)).thenReturn(Optional.empty());

        // When: Fetch admin by unknown email
        Optional<Admin> foundAdmin = adminService.getAdminByEmail(unknownEmail);

        // Then: Ensure empty result is returned
        assertFalse(foundAdmin.isPresent());
    }
}