fetch('/currencies/fetch')
    .then((response) => response.json())
    .then((json) => {
        json.forEach((currency) => {
            $('#from_currency')
                .append(`<option value="${currency.name}">${currency.name}</option>`);
            $('#to_currency')
                .append(`<option value="${currency.name}">${currency.name}</option>`);
        });
    })
    .catch((err) => console.log(err));

$('#convert').on('click', function(e){
    e.preventDefault();

    let fromCurrencyName = $('#from_currency option:selected').text();
    let toCurrencyName = $('#to_currency option:selected').text();
    let amount = $('#amount').val();

    fetch('/currencies/fetch')
        .then((response) => response.json())
        .then((json) => {
            let fromCurrencyRate;
            let fromCurrencyPerUnitOfCurrency;
            let toCurrencyRate;
            let toCurrencyPerUnitOfCurrency;

            json.forEach((currency) => {
                if(currency.name === fromCurrencyName){
                    fromCurrencyRate = currency.rate;
                    fromCurrencyPerUnitOfCurrency = currency.perUnitOfCurrency;

                }
                if(currency.name === toCurrencyName){
                    toCurrencyRate = currency.rate;
                    toCurrencyPerUnitOfCurrency = currency.perUnitOfCurrency;

                }
            });

            let result = (amount / fromCurrencyPerUnitOfCurrency)
                * fromCurrencyRate / toCurrencyRate * toCurrencyPerUnitOfCurrency;

            $('#result').val(result.toFixed(5));

        })
        .catch((err) => console.log(err));
});