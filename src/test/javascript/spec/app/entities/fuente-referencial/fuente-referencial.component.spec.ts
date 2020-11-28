import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SusaludTestModule } from '../../../test.module';
import { FuenteReferencialComponent } from 'app/entities/fuente-referencial/fuente-referencial.component';
import { FuenteReferencialService } from 'app/entities/fuente-referencial/fuente-referencial.service';
import { FuenteReferencial } from 'app/shared/model/fuente-referencial.model';

describe('Component Tests', () => {
  describe('FuenteReferencial Management Component', () => {
    let comp: FuenteReferencialComponent;
    let fixture: ComponentFixture<FuenteReferencialComponent>;
    let service: FuenteReferencialService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [FuenteReferencialComponent],
      })
        .overrideTemplate(FuenteReferencialComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FuenteReferencialComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FuenteReferencialService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new FuenteReferencial(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.fuenteReferencials && comp.fuenteReferencials[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
