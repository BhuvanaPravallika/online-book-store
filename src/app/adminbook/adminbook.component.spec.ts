import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminbookComponent } from './adminbook.component';

describe('AdminbookComponent', () => {
  let component: AdminbookComponent;
  let fixture: ComponentFixture<AdminbookComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminbookComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminbookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
