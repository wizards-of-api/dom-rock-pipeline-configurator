export const PERIOD_TYPES = ['Dias', 'Meses', 'Anos', 'Semanas'] as const
type PeriodType = (typeof PERIOD_TYPES)[number]

export type MetadataConfig = {
	name: string
    fileName: string
	fileOrigin: string
	frequencyNumber: number
	frequencyType: PeriodType
}

export const VALID_COLUMN_TYPES = ['TEXTO', 'INTEIRO', 'DECIMAL', 'VERDADEIRO / FALSO', 'DATA'] as const
export type ValidColumnType = (typeof VALID_COLUMN_TYPES)[number]

export type ColumnConfig = {
	columnId?: Number
	columnNumber?: Number
	columnName?: string
	description?: string
	canBeNull?: number
	status?: number
	type?: string
	hash?: number
	valid?:number
}

export type BronzeConfig = {
	fileId: number
	fileName: string
	fileExtension: string
	frequencyType: string
	frequencyNumber: number
	name: string
	columns: Array<{
		columnId: Number
		columnNumber: Number
		columnName: string
		description: string
		canBeNull: number
		status: number
		type: string
        hash: number
        valid:number
	}>
}
