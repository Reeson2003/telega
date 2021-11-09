declare const firstName: String
declare const lastName: String

const main = () => {
    let newCustomers: { firstName: String; lastName: String }[] = Array.of(...state.customers)
        .concat({
            firstName: firstName,
            lastName: lastName
        });
    newState.set({
        customers: newCustomers
    });
    result.set(newCustomers);
}

main();