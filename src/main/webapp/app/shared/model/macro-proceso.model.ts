import { IProceso } from 'app/shared/model/proceso.model';

export interface IMacroProceso {
  id?: number;
  idMacroproceso?: number;
  maFechaCreacion?: string;
  verificadorIdFerificador?: number;
  idMacroprocesos?: IProceso[];
}

export class MacroProceso implements IMacroProceso {
  constructor(
    public id?: number,
    public idMacroproceso?: number,
    public maFechaCreacion?: string,
    public verificadorIdFerificador?: number,
    public idMacroprocesos?: IProceso[]
  ) {}
}
