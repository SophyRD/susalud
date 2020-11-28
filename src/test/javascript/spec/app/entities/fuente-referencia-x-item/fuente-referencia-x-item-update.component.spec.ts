import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SusaludTestModule } from '../../../test.module';
import { FuenteReferenciaXItemUpdateComponent } from 'app/entities/fuente-referencia-x-item/fuente-referencia-x-item-update.component';
import { FuenteReferenciaXItemService } from 'app/entities/fuente-referencia-x-item/fuente-referencia-x-item.service';
import { FuenteReferenciaXItem } from 'app/shared/model/fuente-referencia-x-item.model';

describe('Component Tests', () => {
  describe('FuenteReferenciaXItem Management Update Component', () => {
    let comp: FuenteReferenciaXItemUpdateComponent;
    let fixture: ComponentFixture<FuenteReferenciaXItemUpdateComponent>;
    let service: FuenteReferenciaXItemService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [FuenteReferenciaXItemUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(FuenteReferenciaXItemUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FuenteReferenciaXItemUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FuenteReferenciaXItemService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new FuenteReferenciaXItem(123);
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
        const entity = new FuenteReferenciaXItem();
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
