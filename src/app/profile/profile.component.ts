import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { LoginserviceService } from '../loginservice.service';
import { Location } from '@angular/common';
import { User } from './User';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  userId: number;
  user: any = {};  // holds user data

  constructor(private loginService: LoginserviceService,private location: Location,private router:Router) {}

ngOnInit(): void {
  const userObj = this.loginService.getUser();

  if (userObj && userObj.userId) {
    this.userId = userObj.userId;
    this.getUserProfile();
  } else {
    alert('User not found. Please login first.');
  }
}


goBack(): void {
  this.location.back();
}

getUserProfile(): void {
  this.loginService.getUserProfile(this.userId).subscribe(
    data => {
      this.user = data;
      if (!this.user || !this.user.firstName) {
        alert('User details are missing.');
      }
    },
    error => {
      alert('User not found. Please log in again.');
      console.error('API error:', error);
    }
  );
}

  updateProfile(): void {
    this.loginService.updateUserProfile(this.userId, this.user).subscribe(
      data => {
        alert('Profile updated successfully');
      },
      error => {
        console.error('Error updating profile', error);
        alert('Failed to update profile');
      }
    );
  }


}