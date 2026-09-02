## What and why

<!-- One or two sentences. What changes, and what problem it solves. -->

## How to verify

<!-- Commands, endpoints, request/response examples. -->

## Checklist

- [ ] `./gradlew build` is green
- [ ] One logical change; unrelated fixes are not smuggled in
- [ ] Entities do not leave the service layer (DTOs only)
- [ ] Validation lives on request DTOs
- [ ] No credentials, no `.env`, no local config committed
- [ ] `CLAUDE.md` updated if a convention or a known-debt item changed
