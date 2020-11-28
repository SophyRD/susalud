import { Moment } from 'moment';

export interface IFuenteReferenciaXItem {
  id?: number;
  idFuenteReferenciaXItem?: number;
  friFechaCreacion?: Moment;
  friFechaModificacion?: Moment;
  friNombre?: string;
}

export class FuenteReferenciaXItem implements IFuenteReferenciaXItem {
  constructor(
    public id?: number,
    public idFuenteReferenciaXItem?: number,
    public friFechaCreacion?: Moment,
    public friFechaModificacion?: Moment,
    public friNombre?: string
  ) {}
}
