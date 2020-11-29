import { ISubProceso } from 'app/shared/model/sub-proceso.model';
import { IMacroProceso } from 'app/shared/model/macro-proceso.model';

export interface IProceso {
  id?: number;
  idProceso?: number;
  prDescripcion?: string;
  macroprocesoIdMacroproseso?: number;
  macroprocesoVerificadorIdVerificador?: number;
  idProcesos?: ISubProceso[];
  macroProceso?: IMacroProceso;
}

export class Proceso implements IProceso {
  constructor(
    public id?: number,
    public idProceso?: number,
    public prDescripcion?: string,
    public macroprocesoIdMacroproseso?: number,
    public macroprocesoVerificadorIdVerificador?: number,
    public idProcesos?: ISubProceso[],
    public macroProceso?: IMacroProceso
  ) {}
}
