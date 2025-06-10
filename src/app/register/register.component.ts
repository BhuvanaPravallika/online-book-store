import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginserviceService } from '../loginservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm!: FormGroup;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private loginService: LoginserviceService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      fname: ['', Validators.required],
      lname: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: [
        '',
        [
          Validators.required,
          Validators.minLength(6),
          Validators.pattern(
            '^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z\\d])[A-Za-z\\d\\W]{6,}$'
          )
        ]
      ],
      confirmPassword: ['', Validators.required]
    }, { validators: this.passwordMatchValidator });
  }

  passwordMatchValidator(group: FormGroup): { [key: string]: boolean } | null {
    const password = group.get('password')?.value;
    const confirmPassword = group.get('confirmPassword')?.value;

    if (password !== confirmPassword) {
      group.get('confirmPassword')?.setErrors({ passwordMismatch: true });
      return { passwordMismatch: true };
    } else {
      const confirmControl = group.get('confirmPassword');
      if (confirmControl?.hasError('passwordMismatch')) {
        confirmControl.setErrors(null);
      }
      return null;
    }
  }

  get f() {
    return this.registerForm.controls;
  }

  onSubmit(): void {
    this.submitted = true;

    if (this.registerForm.invalid) {
      return;
    }

    const { fname, lname, email, password } = this.registerForm.value;

    const userPayload = {
      firstName: fname,
      lastName: lname,
      email,
      password
    };

    this.loginService.registerUser(userPayload).subscribe(
      (response) => {
        console.log('Registration successful', response);
        alert("Registration successful");

        // ✅ Clear form and reset state after success
        this.registerForm.reset();
        this.submitted = false;
      },
      (error) => {
        console.error('Registration failed', error);

        let errorMessage = 'Registration failed due to unknown error.';
        if (error.status === 400) {
          if (typeof error.error === 'string') {
            errorMessage = error.error;
          } else if (error.error?.message) {
            errorMessage = error.error.message;
          }
        }

        alert(errorMessage);

        // Optionally reset form on failure
        this.registerForm.reset();
        this.submitted = false;
      }
    );
  }
}