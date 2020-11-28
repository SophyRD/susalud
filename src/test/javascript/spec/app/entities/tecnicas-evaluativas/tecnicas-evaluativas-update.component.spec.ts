import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SusaludTestModule } from '../../../test.module';
import { TecnicasEvaluativasUpdateComponent } from 'app/entities/tecnicas-evaluativas/tecnicas-evaluativas-update.component';
import { TecnicasEvaluativasService } from 'app/entities/tecnicas-evaluativas/tecnicas-evaluativas.service';
import { TecnicasEvaluativas } from 'app/shared/model/tecnicas-evaluativas.model';

describe('Component Tests', () => {
  describe('TecnicasEvaluativas Management Update Component', () => {
    let comp: TecnicasEvaluativasUpdateComponent;
    let fixture: ComponentFixture<TecnicasEvaluativasUpdateComponent>;
    let service: TecnicasEvaluativasService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [TecnicasEvaluativasUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TecnicasEvaluativasUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TecnicasEvaluativasUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TecnicasEvaluativasService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TecnicasEvaluativas(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new TecnicasEvaluativas();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
