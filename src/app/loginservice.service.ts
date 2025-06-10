import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './profile/User';

@Injectable({
  providedIn: 'root'
})
export class LoginserviceService {

  private apiUrl = 'http://localhost:8080/users';  // Adjust URL accordingly

  constructor(private http: HttpClient) { }
  login(userCredentials: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, userCredentials);
  }
  registerUser(user: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, user);  // Example POST request
  }

  private userKey = 'user';



setUser(user: any) {
  console.log('Setting user in localStorage:', user);  // Check for id here
  localStorage.setItem(this.userKey, JSON.stringify(user));
}

getUser(): any {
  const userStr = localStorage.getItem(this.userKey);
  if (!userStr) return null;

  try {
    const user = JSON.parse(userStr);
    // Accept user if any of these id keys exist
    if (user && (user.userId || user.user_Id || user.id)) {
      return user;
    }
    return null;
  } catch (e) {
    return null;
  }
}

isLoggedIn(): boolean {
  return this.getUser() !== null;
}



getLoggedInUser(): any {
  const user = localStorage.getItem('loggedInUser');
  if (user) {
    return JSON.parse(user);
  }
  return null;
}

getUserById(id: number): Observable<any> {
  return this.http.get(`${this.apiUrl}/profile/${id}`);  // Adjust this URL if your actual endpoint is different
}

  logout() {
    localStorage.removeItem(this.userKey);
  }

   // Update the user profile
updateProfile(userId: number, updatedUser: any): Observable<any> {
  return this.http.put(`${this.apiUrl}/update/${userId}`, updatedUser);
}



getUserProfile(id: number): Observable<any> {
  return this.http.get(`http://localhost:8080/users/profile/${id}`);
}

updateUserProfile(id: number, user: any): Observable<any> {
  return this.http.put(`http://localhost:8080/users/update/${id}`, user);
}



}
