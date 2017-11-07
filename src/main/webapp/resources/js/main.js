$(function () {
    function ReDebtrCore() {
        var $body = $("body");

        this.initPlugins = function ($element) {
            ($element || $body).find('[data-toggle="tooltip"]').tooltip();
            enableBsValidatorOn($element);
        };

        function enableBsValidatorOn($element) {
            ($element || $body).find('form.bs-validation').each(function () {
                var form = this;
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });

            ($element || $body).find(".form-group.has-error").each(function () {
                console.log(this);
            });
        }
    }

    window.widgets = new ReDebtrCore();

    window.widgets.initPlugins();
});
