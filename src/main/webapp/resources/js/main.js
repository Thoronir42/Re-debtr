$(function () {
    function ReDebtrCore() {
        this.initPlugins = function ($element) {
            if(!$element) {
                $element = $("body");
            }

            $element.find('[data-toggle="tooltip"]').tooltip();
        }
    }

    window.widgets = new ReDebtrCore();

    window.widgets.initPlugins();
});
