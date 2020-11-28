import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SusaludTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { CriteriosEvaluacionDeleteDialogComponent } from 'app/entities/criterios-evaluacion/criterios-evaluacion-delete-dialog.component';
import { CriteriosEvaluacionService } from 'app/entities/criterios-evaluacion/criterios-evaluacion.service';

describe('Component Tests', () => {
  describe('CriteriosEvaluacion Management Delete Component', () => {
    let comp: CriteriosEvaluacionDeleteDialogComponent;
    let fixture: ComponentFixture<CriteriosEvaluacionDeleteDialogComponent>;
    let service: CriteriosEvaluacionService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SusaludTestModule],
        declarations: [CriteriosEvaluacionDeleteDialogComponent],
      })
        .overrideTemplate(CriteriosEvaluacionDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CriteriosEvaluacionDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CriteriosEvaluacionService);
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
