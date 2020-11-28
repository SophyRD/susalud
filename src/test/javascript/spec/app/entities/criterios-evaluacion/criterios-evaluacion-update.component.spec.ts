import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SusaludTestModule } from '../../../test.module';
import { CriteriosEvaluacionUpdateComponent } from 'app/entities/criterios-evaluacion/criterios-evaluacion-update.component';
import { CriteriosEvaluacionService } from 'app/entities/criterios-evaluacion/criterios-evaluacion.service';
import { CriteriosEvaluacion } from 'app/shared/model/criterios-evaluacion.model';

describe('Component Tests', () => {
  describe('CriteriosEvaluacion Management Update Component', () => {
    let comp: CriteriosEvaluacionUpdateComponent;
    let fixture: ComponentFixture<CriteriosEvaluacionUpdateComponent>;
    let service: CriteriosEvaluacionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [CriteriosEvaluacionUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CriteriosEvaluacionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CriteriosEvaluacionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CriteriosEvaluacionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CriteriosEvaluacion(123);
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
        const entity = new CriteriosEvaluacion();
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
