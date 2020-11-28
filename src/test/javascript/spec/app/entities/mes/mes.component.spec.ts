import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SusaludTestModule } from '../../../test.module';
import { MesComponent } from 'app/entities/mes/mes.component';
import { MesService } from 'app/entities/mes/mes.service';
import { Mes } from 'app/shared/model/mes.model';

describe('Component Tests', () => {
  describe('Mes Management Component', () => {
    let comp: MesComponent;
    let fixture: ComponentFixture<MesComponent>;
    let service: MesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [MesComponent],
      })
        .overrideTemplate(MesComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MesComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MesService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Mes(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.mes && comp.mes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
