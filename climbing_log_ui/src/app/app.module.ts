import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { NewsCardComponent } from './components/news-card/news-card.component';
import { HomeComponent } from './home/home.component';
import { BouldersComponent } from './boulders/boulders.component';
import { RouteClimbsComponent } from './route-climbs/route-climbs.component';
import { SideNavComponent } from './components/side-nav/side-nav.component';
import { SearchComponent } from './components/search/search.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    NewsCardComponent,
    HomeComponent,
    BouldersComponent,
    RouteClimbsComponent,
    SideNavComponent,
    SearchComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
