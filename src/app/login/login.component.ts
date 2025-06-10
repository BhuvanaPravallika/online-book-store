import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginserviceService } from '../loginservice.service';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent  implements OnInit {
  loginForm!: FormGroup;
  submitted = false;  // This will track whether the form has been submitted

  // Error handling
  errorMessage: string = '';

  // Static admin credentials
  adminEmail = 'Bhuvana@gmail.com';
  adminPassword = 'Bhuvana@123';

  constructor(private fb: FormBuilder, private loginService: LoginserviceService, private router: Router) { }

  ngOnInit() {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  // Get form controls for easier access in the template
  get f() {
    return this.loginForm.controls;
  }

  // Form submission handler
  onSubmit() {
  this.submitted = true;

  if (this.loginForm.invalid) {
    return;
  }

  const email = this.loginForm.value.email;
  const password = this.loginForm.value.password;

  // Check admin static credentials
  if (email === this.adminEmail && password === this.adminPassword) {
    localStorage.setItem('isAdmin', 'true');  // Mark admin login
    localStorage.setItem('userEmail', email);
    this.errorMessage = '';
    this.router.navigate(['/adminbook']);
    return; // Skip user login service call
  }

  // User login flow
  this.loginService.login({ email, password }).subscribe(
    user => {
      localStorage.setItem('isAdmin', 'false'); // Not admin
      this.loginService.setUser(user);
      this.router.navigate(['/home']);
    },
    (error: HttpErrorResponse) => {
      if (error.status === 401) {
        this.errorMessage = 'Invalid credentials. Please check your email and password.';
      } else {
        this.errorMessage = 'An error occurred while logging in. Please try again later.';
      }
    }
  );
}

  isAdmin(): boolean {
  return localStorage.getItem('isAdmin') === 'true';
}

isLoggedIn(): boolean {
  // You may also check if token or user info exists for normal user
  return localStorage.getItem('isAdmin') !== null;
}

logout() {
  localStorage.clear();
  this.router.navigate(['/login']);
}

}