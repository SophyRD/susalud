import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SusaludTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { CriteriosEvaluacionXItemDeleteDialogComponent } from 'app/entities/criterios-evaluacion-x-item/criterios-evaluacion-x-item-delete-dialog.component';
import { CriteriosEvaluacionXItemService } from 'app/entities/criterios-evaluacion-x-item/criterios-evaluacion-x-item.service';

describe('Component Tests', () => {
  describe('CriteriosEvaluacionXItem Management Delete Component', () => {
    let comp: CriteriosEvaluacionXItemDeleteDialogComponent;
    let fixture: ComponentFixture<CriteriosEvaluacionXItemDeleteDialogComponent>;
    let service: CriteriosEvaluacionXItemService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [CriteriosEvaluacionXItemDeleteDialogComponent],
      })
        .overrideTemplate(CriteriosEvaluacionXItemDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CriteriosEvaluacionXItemDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CriteriosEvaluacionXItemService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
