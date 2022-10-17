import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { BouldersComponent } from './boulders/boulders.component';
import { RouteClimbsComponent } from './route-climbs/route-climbs.component';
import { SideNavComponent } from './components/side-nav/side-nav.component';
import { SearchComponent } from './components/search/search.component';
import { MyClimbsComponent } from './my-climbs/my-climbs.component';
import { NewsCardComponent } from './components/news-card/news-card.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { LoginComponent } from './login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input'
import { MatIconModule } from '@angular/material/icon';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    NewsCardComponent,
    HomeComponent,
    BouldersComponent,
    RouteClimbsComponent,
    SideNavComponent,
    SearchComponent,
    MyClimbsComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    MatIconModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
