import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import { CartComponent } from './cart/cart.component';
import { OrderComponent } from './order/order.component';
import { AdminbookComponent } from './adminbook/adminbook.component';
import { AddBookComponent } from './add-book/add-book.component';
import { UpdateBookComponent } from './update-book/update-book.component';


const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },

  {
    path:"register",
    component:RegisterComponent
  },
  {
    path:"login",
    component:LoginComponent
  },
  {
    path:"home",
    component:HomeComponent
  },
  {
    path:"profile",
    component:ProfileComponent
  },
  {
    path:"edit-profile",
    component:EditProfileComponent
  },
  { path: 'orders',
     component: OrderComponent
  },

   {
    path:"cart",
    component:CartComponent
  },
  {
    path:"adminbook",
    component:AdminbookComponent
  },
  {
    path:"add-book",
    component:AddBookComponent
  },
 { path: 'update-book/:bookId', component: UpdateBookComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
