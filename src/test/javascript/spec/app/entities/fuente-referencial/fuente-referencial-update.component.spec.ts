import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SusaludTestModule } from '../../../test.module';
import { FuenteReferencialUpdateComponent } from 'app/entities/fuente-referencial/fuente-referencial-update.component';
import { FuenteReferencialService } from 'app/entities/fuente-referencial/fuente-referencial.service';
import { FuenteReferencial } from 'app/shared/model/fuente-referencial.model';

describe('Component Tests', () => {
  describe('FuenteReferencial Management Update Component', () => {
    let comp: FuenteReferencialUpdateComponent;
    let fixture: ComponentFixture<FuenteReferencialUpdateComponent>;
    let service: FuenteReferencialService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [FuenteReferencialUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(FuenteReferencialUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FuenteReferencialUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FuenteReferencialService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new FuenteReferencial(123);
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
        const entity = new FuenteReferencial();
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
