import { Component, OnInit } from '@angular/core';
import { LoginserviceService } from '../loginservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {
  user: any = {};

  constructor(private loginService: LoginserviceService, private router: Router) {}

  ngOnInit(): void {
    const storedUser = this.loginService.getUser();
    if (storedUser && storedUser.id) {
      this.user = { ...storedUser };  // Create a copy to edit
    } else {
      console.error('User not found');
      this.router.navigate(['/login']);
    }
  }

  saveChanges(): void {
    if (!this.user || !this.user.id) {
      console.error('User ID missing');
      return;
    }

    this.loginService.updateProfile(this.user.id, this.user).subscribe(
      (response) => {
        console.log('Profile updated:', response);

        // Update localStorage with latest data
        localStorage.setItem('user', JSON.stringify(response));

        // Navigate back to profile
        this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
          this.router.navigate(['/profile']);
        });
      },
      (error) => {
        console.error('Update failed:', error);
      }
    );
  }
}