/**
 * Generated by Gas3 v2.3.0 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR.
 */

package org.openforis.collect.model {

    import org.granite.util.Enum;

    [Bindable]
    [RemoteClass(alias="org.openforis.collect.model.RecordSummarySortField$Sortable")]
    public class RecordSummarySortField$Sortable extends Enum {

        public static const KEY1:RecordSummarySortField$Sortable = new RecordSummarySortField$Sortable("KEY1", _);
        public static const KEY2:RecordSummarySortField$Sortable = new RecordSummarySortField$Sortable("KEY2", _);
        public static const KEY3:RecordSummarySortField$Sortable = new RecordSummarySortField$Sortable("KEY3", _);
        public static const COUNT1:RecordSummarySortField$Sortable = new RecordSummarySortField$Sortable("COUNT1", _);
        public static const COUNT2:RecordSummarySortField$Sortable = new RecordSummarySortField$Sortable("COUNT2", _);
        public static const COUNT3:RecordSummarySortField$Sortable = new RecordSummarySortField$Sortable("COUNT3", _);
        public static const SKIPPED:RecordSummarySortField$Sortable = new RecordSummarySortField$Sortable("SKIPPED", _);
        public static const MISSING:RecordSummarySortField$Sortable = new RecordSummarySortField$Sortable("MISSING", _);
        public static const ERRORS:RecordSummarySortField$Sortable = new RecordSummarySortField$Sortable("ERRORS", _);
        public static const WARNINGS:RecordSummarySortField$Sortable = new RecordSummarySortField$Sortable("WARNINGS", _);
        public static const DATE_CREATED:RecordSummarySortField$Sortable = new RecordSummarySortField$Sortable("DATE_CREATED", _);
        public static const DATE_MODIFIED:RecordSummarySortField$Sortable = new RecordSummarySortField$Sortable("DATE_MODIFIED", _);
        public static const STEP:RecordSummarySortField$Sortable = new RecordSummarySortField$Sortable("STEP", _);

        function RecordSummarySortField$Sortable(value:String = null, restrictor:* = null) {
            super((value || KEY1.name), restrictor);
        }

        protected override function getConstants():Array {
            return constants;
        }

        public static function get constants():Array {
            return [KEY1, KEY2, KEY3, COUNT1, COUNT2, COUNT3, SKIPPED, MISSING, ERRORS, WARNINGS, DATE_CREATED, DATE_MODIFIED, STEP];
        }

        public static function valueOf(name:String):RecordSummarySortField$Sortable {
            return RecordSummarySortField$Sortable(KEY1.constantOf(name));
        }
    }
}