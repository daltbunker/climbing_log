import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BouldersComponent } from './boulders/boulders.component';
import { HomeComponent } from './home/home.component';
import { RouteClimbsComponent } from './route-climbs/route-climbs.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'routes', component: RouteClimbsComponent },
  { path: 'boulders', component: BouldersComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
