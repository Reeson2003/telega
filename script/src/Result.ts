interface Reference<T> {
    set: (data: T) => void;
    get: () => T
}

declare const result: Reference<any>;

declare const newState: Reference<State>;