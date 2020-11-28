import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SusaludTestModule } from '../../../test.module';
import { TecnicasEvaluativasComponent } from 'app/entities/tecnicas-evaluativas/tecnicas-evaluativas.component';
import { TecnicasEvaluativasService } from 'app/entities/tecnicas-evaluativas/tecnicas-evaluativas.service';
import { TecnicasEvaluativas } from 'app/shared/model/tecnicas-evaluativas.model';

describe('Component Tests', () => {
  describe('TecnicasEvaluativas Management Component', () => {
    let comp: TecnicasEvaluativasComponent;
    let fixture: ComponentFixture<TecnicasEvaluativasComponent>;
    let service: TecnicasEvaluativasService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [TecnicasEvaluativasComponent],
      })
        .overrideTemplate(TecnicasEvaluativasComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TecnicasEvaluativasComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TecnicasEvaluativasService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TecnicasEvaluativas(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tecnicasEvaluativas && comp.tecnicasEvaluativas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
