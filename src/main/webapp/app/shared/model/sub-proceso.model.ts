import { IProceso } from 'app/shared/model/proceso.model';

export interface ISubProceso {
  id?: number;
  idSubproceso?: number;
  spDescripcion?: string;
  procesoIdProceso?: number;
  procesoMacroprocesoIdMacroproceso?: number;
  procesoMacroprocesoVerificadorIdVerificador?: number;
  proceso?: IProceso;
}

export class SubProceso implements ISubProceso {
  constructor(
    public id?: number,
    public idSubproceso?: number,
    public spDescripcion?: string,
    public procesoIdProceso?: number,
    public procesoMacroprocesoIdMacroproceso?: number,
    public procesoMacroprocesoVerificadorIdVerificador?: number,
    public proceso?: IProceso
  ) {}
}
